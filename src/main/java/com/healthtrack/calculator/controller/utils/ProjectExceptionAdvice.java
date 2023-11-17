package com.healthtrack.calculator.controller.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.healthtrack.calculator.annotation.Warning;
import com.healthtrack.calculator.exception.JsonRepresentationException;
import com.healthtrack.calculator.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ProjectExceptionAdvice {

    @ExceptionHandler
    @Warning(Warning.Type.ADDITIONAL_FEATURE)
    public void doSystemException(Exception exception){
        log.error("Customized error: " + exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.warn("NULL OR EMPTY FIELDS DETECTED - TRIGGER ERROR RESPONSE");
        Map<String, Object> response = new HashMap<>();
        response.put("flag", false);
        response.put("message", "compulsory fields left empty");
        response.put("statusCode", 400);
        return response;
    }

    @ExceptionHandler({JsonRepresentationException.class, JsonParseException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Object> handleJsonFormatExceptions(MethodArgumentNotValidException ex) {
        log.warn("JSON FORMAT ERROR - TRIGGER ERROR RESPONSE");
        Map<String, Object> response = new HashMap<>();
        response.put("flag", false);
        response.put("message", "JSON FORMAT ERROR");
        response.put("statusCode", 400);
        return response;
    }
}
