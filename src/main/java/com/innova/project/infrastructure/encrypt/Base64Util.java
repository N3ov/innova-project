package com.innova.project.infrastructure.encrypt;

import com.innova.project.infrastructure.config.GlobalConfig;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

public class Base64Util {

    private static final Charset CHAR_SET = GlobalConfig.CHAR_SET;

    public static String encode2String(byte[] bytes) {
        if (null == bytes) return null;
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String encode2String(String str){
        if(null == str) return null;
        return Base64.getEncoder().encodeToString(str.getBytes(CHAR_SET));
    }

    public static String decode2String(String str){
        if (null == str) return null;
        return new String(Base64.getDecoder().decode(str), CHAR_SET);
    }

    public static byte[] decode(String str) {
        if (null == str) return null;
        return Base64.getDecoder().decode(str);
    }
}
