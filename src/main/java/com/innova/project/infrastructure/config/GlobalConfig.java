package com.innova.project.infrastructure.config;

import lombok.experimental.UtilityClass;

import java.nio.charset.Charset;

@UtilityClass
public class GlobalConfig {

    public static final String CHARSET_NAME = "UTF-8";
    public static final Charset CHAR_SET = Charset.forName(CHARSET_NAME);

}
