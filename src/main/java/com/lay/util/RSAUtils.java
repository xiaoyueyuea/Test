/*******************************************************************************
 *  Copyright (c) 2019, 2019 Dauron Corporation.
 ******************************************************************************/

package com.lay.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.CharUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class RSAUtils {

    /**
     * 加密算法
     */
    private static final String KEY_ALGORITHM = "RSA";

    private static final int MAX_ENCRYPT_BLOCK = 117;

    private static final int MAX_DECRYPT_BLOCK = 128;


    /**
     * 数据rsa加密
     * @param plainText 明文
     * @param key 海尔提供的私钥
     */
    public static String encryptRSA(String plainText, String key) throws Exception {
        byte[] buffer = plainText.getBytes(StandardCharsets.UTF_8);
        buffer = compress(buffer);
        buffer = encryptByKey(buffer, key);
        return stripNewLine(Base64.encodeBase64String(buffer));
    }

    /**
     * 数据rsa解密, 与加密过程相反
     * @param cipherText 密文
     * @param key 海尔自己持有的公钥
     */
    public static String decryptRSA(String cipherText, String key) throws Exception {
        byte[] buffer = Base64.decodeBase64(cipherText);
        buffer = decryptByKey(buffer, key);
        buffer = decompress(buffer);
        return stripNewLine(new String(buffer, StandardCharsets.UTF_8));
    }

    private static String stripNewLine(String value) {
        if (value == null || value.length() == 0) {
            return value;
        }
        StringBuilder builder = new StringBuilder(value.length());
        for (char c : value.toCharArray()) {
            switch (c) {
                case CharUtils.CR:
                case CharUtils.LF:
                    break;
                default:
                    builder.append(c);
                    break;
            }
        }
        return builder.toString();
    }

    /**
     * 数据压缩
     * @param buffer
     */
    private static byte[] compress(byte[] buffer) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream)) {
                gzipOutputStream.write(buffer);
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 数据解压
     * @param buffer
     */
    private static byte[] decompress(byte[] buffer) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
            GZIPInputStream gzip = new GZIPInputStream(bis);
            ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int num = -1;
            while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                bos.write(buf, 0, num);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密
     * @param data 源数据
     * @param key 海尔提供的私钥
     */
    private static byte[] encryptByKey(byte[] data, String key)
        throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key);

        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, priKey);
        int inputLen = data.length;
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            return out.toByteArray();
        }
    }

    /**
     *
     * @param encryptedData 已加密数据
     * @param key 海尔自己持有的公钥
     */
    public static byte[] decryptByKey(byte[] encryptedData, String key)
        throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

}
