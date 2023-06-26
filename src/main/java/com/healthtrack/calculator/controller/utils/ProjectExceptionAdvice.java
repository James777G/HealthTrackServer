package com.healthtrack.calculator.controller.utils;

import com.healthtrack.calculator.annotation.Warning;
import com.healthtrack.calculator.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ProjectExceptionAdvice {

    @ExceptionHandler
    @Warning(Warning.Type.ADDITIONAL_FEATURE)
    public void doSystemException(Exception exception){
        log.error("Customized error: " + exception.getMessage());
    }
}
