package com.healthtrack.calculator.service.symptomFinder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.healthtrack.calculator.utils.JsonUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("symptomFinderServiceAdapter")
public class SymptomFinderAdapterImpl implements SymptomFinderServiceAdapter{

    private static final String ATTRIBUTE_NAME = "symptoms";

    @Override
    public String forwardAdapting(Map<String, List<String>> map) throws JsonProcessingException {

        // Change To Lower Case And Replace " " with "_"
        List<String> list = map.get(ATTRIBUTE_NAME).stream()
                .map(s -> {
                    String lowerCaseResult = s.toLowerCase();
                    String beforeParenthesis = lowerCaseResult.split("\\(", 2)[0].trim();
                    lowerCaseResult = beforeParenthesis.replace(" ", "_");
                    return lowerCaseResult;
                }).toList();

        map.replace(ATTRIBUTE_NAME, list);

        return JsonUtil.objectToJson(map);

    }


    @Override
    @SuppressWarnings("all")
    public Map<String, Double> backwardAdapting(Object jsonObject) throws JsonProcessingException {
        return JsonUtil.jsonToObject(jsonObject.toString(), HashMap.class);
    }
}
