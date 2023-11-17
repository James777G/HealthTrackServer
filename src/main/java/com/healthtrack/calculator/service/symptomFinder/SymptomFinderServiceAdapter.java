package com.healthtrack.calculator.service.symptomFinder;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface SymptomFinderServiceAdapter {

    /**
     * Convert Map to Json String
     * @param map request body
     * @return String in Json Representation
     */
    String forwardAdapting(Map<String, List<String>> map) throws JsonProcessingException;


    Map<String, Double> backwardAdapting(Object jsonObject) throws JsonProcessingException;
}
