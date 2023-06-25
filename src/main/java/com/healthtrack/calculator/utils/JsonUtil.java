package com.healthtrack.calculator.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Json Util class providing basic operations in Json-Object (de)serialization
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Convert a Java object to JSON string.
     */
    public static String objectToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * Convert a JSON string to Java object.
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }
}
