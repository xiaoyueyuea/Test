package com.lay.run;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lay.bean.BHReportQueryResponseBean;
import com.lay.util.RSAUtils;
import com.lay.util.SignUtil;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/8/3 11:05
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/8/3 lei.yue 1.0 create file
 */
public class RSATest {

    private final static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUeuHJj74KZQvMckTuWdzz3rXHZdN+1JuFdupSecec19jmaNXu9WI/8PRrFOgWKJsRzL+xw4dUHNDj7ehRkP/uqjrwe33hdiAeuwFpUFnEvGglFF3IY9Jnm1kY+Ff6aFrhCouXaKwqhwRmZ+4dQ6X98/8QUaj2Xl63wRynZfxQDwIDAQAB";

    private final static String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANR64cmPvgplC8xyRO5Z3PPetcdl037Um4V26lJ5x5zX2OZo1e71Yj/w9GsU6BYomxHMv7HDh1Qc0OPt6FGQ/+6qOvB7feF2IB67AWlQWcS8aCUUXchj0mebWRj4V/poWuEKi5dorCqHBGZn7h1Dpf3z/xBRqPZeXrfBHKdl/FAPAgMBAAECgYEAlTgrIwugsdoevBiv0hgn9Ng+Huei7RpQkh1eGEfP6JUpjT6op2WK9063yA/poc4ogQqiwSuI+Yg3JysoEUhcgy9QXMNpiprAdSI5RvnYoSGwBsHA7LwwLj0wlrsgpKffJcrDehP2Bol/+M7ueD7n3TOFm56LFSIU3c7yhMinIKECQQDqwFC7CP67aRqvsXSWSM68LSFlEVYc07NEttgehR/fEs95EKwfEHy4gmFUi6wgp0Uom+3luI1CXQgRk0W5t9Q/AkEA57Z+vGC3kKoUKkqdekDeRVQrczqLZrBUrBNcacKRmhYELM2NS43nd46Mu4zlreS9gOY+56CVcdp+47xr9kdQMQJAEq/mfU0VAhQ21J+auU/obSBmlSYRJdBQZ/rqL1vHkrlrnKaQHRTx6LowR0urExpdrgLjqDLNw+el+cgwd71DfwJAUfV2HJGlPFE8cr0TuKNrO2CLQeXKWxcxy+/JN0twDu2MqoBGYxwdbGeKleg/cWPAN/C4/VsKoUvkqA8ErQcKcQJBAN0hfbpyqEzcYfsYj97T4tQRMDHbg+BJtG5BgvXDhNvtqr9k84HJNLfO3mvy13F9svG77WbQh3w8XZWmI2mSI8U=";

    public static void main(String[] args) throws Exception {

        //明文
        String plainText = "{\"tradeNumber\":\"\",\"amount\":2665.45,\"period\":12,\"time\":1589733905763}";

        //RSA加密
        String cipherText = RSAUtils.encryptRSA(plainText,PRIVATE_KEY);
        System.out.println("密文：" + cipherText);

        //RSA解密
        String info = RSAUtils.decryptRSA(cipherText,PUBLIC_KEY);
        System.out.println("解密" + info);
        System.out.println(JSON.parseObject(info).getString("amount"));

        //RSA加签
        String sign = SignUtil.sign(plainText,PRIVATE_KEY);
        System.out.println("加签：" + sign);

        //verify(验签，签名与消息对应，防止消息被篡改)
        if(SignUtil.verify(plainText,sign,PUBLIC_KEY)){
            System.out.println("验签成功");
        }else {
            System.out.println("验签失败");
        }
    }
}
