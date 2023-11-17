package com.healthtrack.calculator.service.symptomFinder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.healthtrack.calculator.domain.ResponseBody;
import com.healthtrack.calculator.service.message.MessageService;
import com.healthtrack.calculator.utils.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("symptomFinderService")
public class SymptomFinderServiceProvider implements SymptomFinderService{

    @Resource
    private MessageService messageService;

    @Resource
    private SymptomFinderServiceAdapter symptomFinderServiceAdapter;


    @Override
    public ResponseBody<Map<String, Double>> findDisease(Map<String, List<String>> symptomsMap, String token) throws JsonProcessingException {
        Object o = messageService.sendAndReceive(symptomFinderServiceAdapter.forwardAdapting(symptomsMap));
        Map<String, Double> stringDoubleMap = symptomFinderServiceAdapter.backwardAdapting(o);
        return new ResponseBody<>(true, "Successful", JwtUtil.generateToken(JwtUtil.getUsernameFromToken(token)), 200, stringDoubleMap);
    }
}
