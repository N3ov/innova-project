package com.innova.project.infrastructure.encrypt;

public class ByteUtil {

    private ByteUtil(){};

    public static String toHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        String hex;
        for (byte b : bytes) {
            hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
