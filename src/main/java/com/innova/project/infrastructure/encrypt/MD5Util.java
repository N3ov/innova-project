package com.innova.project.infrastructure.encrypt;

import com.innova.project.infrastructure.config.GlobalConfig;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    private static final Charset CHAR_SET = GlobalConfig.CHAR_SET;
    private static final String MD5 = "MD5";
    private static final String HmacMD5 = "HmacMD5";

    public static byte[] digest(String str) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(MD5).digest(str.getBytes(CHAR_SET));
    }

    public static String digest2String(String str) throws NoSuchAlgorithmException {
        return ByteUtil.toHex(MessageDigest.getInstance(MD5).digest(str.getBytes(CHAR_SET)));
    }

    public static String digestString(String str, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        return ByteUtil.toHex(initMac(key).doFinal(str.getBytes(CHAR_SET)));
    }

    private static Mac initMac(String key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKey secretKey=new SecretKeySpec(key.getBytes(CHAR_SET),HmacMD5);
        Mac mac = Mac.getInstance(HmacMD5);
        mac.init(secretKey);
        return mac;
    }
}
