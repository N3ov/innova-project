package com.innova.project.core.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public class JsonUtil {

    public static <T> T decode(String json, TypeReference<T> valueTypeRef)
            throws IOException {
        if (json == null) return null;
        return create().readValue(json, valueTypeRef);
    }

    public static <T> T decode(String json, Class<T> type)
            throws IOException {
        if (json == null) return null;
        return create().readValue(json, type);
    }

    public static String encode(Object value) throws JsonProcessingException, JsonProcessingException {
        return create().writeValueAsString(value);
    }

    public static String encode(int stringMaxLength, Object value) throws JsonProcessingException {
        return create(stringMaxLength).writeValueAsString(value);
    }

    public static Boolean isValidJson(String maybeJson) {
        try {
            JsonUtil.create().readTree(maybeJson);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static ObjectMapper create(int stringMaxLength) {
        SimpleModule module = new SimpleModule();
        module.addSerializer(String.class, new StringLimitJsonSerializer(stringMaxLength));
        ObjectMapper om = create();
        om.registerModule(module);
        return om;
    }

    public static ObjectMapper create() {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        om.getSerializerProvider().setNullKeySerializer(new NullKeyJsonSerializer());
        return om;
    }

    /**
     * Limit string length
     */
    static class StringLimitJsonSerializer extends JsonSerializer<String> {
        private final int limit;

        StringLimitJsonSerializer(int limit) {
            this.limit = limit;
        }

        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            if (value.length() > limit) {
                value = value.substring(0, limit - 3) + "...";
            }
            gen.writeString(value);
        }
    }

    /**
     * null Key handler
     */
    static class NullKeyJsonSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            gen.writeFieldName("null");
        }
    }
}
