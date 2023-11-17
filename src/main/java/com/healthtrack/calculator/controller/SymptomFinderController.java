package com.healthtrack.calculator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.healthtrack.calculator.domain.ResponseBody;
import com.healthtrack.calculator.service.symptomFinder.SymptomFinderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SymptomFinderController {

    @Resource
    private SymptomFinderService symptomFinderService;

    @PostMapping("/symptomfinder")
    public ResponseBody<Map<String, Double>> findDisease(@RequestBody Map<String, List<String>> symptomsMap, @RequestHeader("JWT_AUTHORIZATION") String token) throws JsonProcessingException {
        return symptomFinderService.findDisease(symptomsMap, token);
    }
}
