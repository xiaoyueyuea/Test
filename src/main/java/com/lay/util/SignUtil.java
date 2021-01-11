package com.lay.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SignUtil {

	public static boolean verify(String content, String sign, String publicKey) {
		return verify(content, sign, publicKey, null);
	}

	public static boolean verify(String content, String sign, String publicKey, String charset) {
		try {
			PublicKey pubKey = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));
			Signature signature = Signature.getInstance("SHA1WithRSA");
			signature.initVerify(pubKey);
			if (charset == null || "".equals(charset)) {
				signature.update(content.getBytes());
			} else {
				signature.update(content.getBytes(charset));
			}
			byte[] decode = Base64.getDecoder().decode(sign.getBytes());
			return signature.verify(decode);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static PublicKey getPublicKeyFromX509(String algorithm, InputStream inputStream) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
			byte[] bytes = new byte[inputStream.available()];
			while (inputStream.read(bytes) != -1) {
			}
			byte[] encodedKey = bytes;
			encodedKey = Base64.getDecoder().decode(encodedKey);
			return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String sign(String content, String privateKey) {
		return sign(content, privateKey, null);
	}

	public static String sign(String content, String privateKey, String charset) {
		try {
			PrivateKey pubKey = getPrivateKeyFromPKCS8("RSA", new ByteArrayInputStream(privateKey.getBytes()));
			Signature signature = Signature.getInstance("SHA1WithRSA");
			signature.initSign(pubKey);
			if (charset == null || "".equals(charset)) {
				signature.update(content.getBytes());
			} else {
				signature.update(content.getBytes(charset));
			}
			return new String(Base64.getEncoder().encode(signature.sign()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream inputStream) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
			byte[] bytes = new byte[inputStream.available()];
			while (inputStream.read(bytes) != -1) {
			}
			byte[] encodedKey = bytes;
			encodedKey = Base64.getDecoder().decode(encodedKey);
			return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
