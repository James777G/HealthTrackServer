package com.healthtrack.calculator.service.symptomFinder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.healthtrack.calculator.domain.ResponseBody;

import java.util.List;
import java.util.Map;

public interface SymptomFinderService {

    ResponseBody<Map<String, Double>> findDisease(Map<String, List<String>> symptomsMap, String token) throws JsonProcessingException;
}
