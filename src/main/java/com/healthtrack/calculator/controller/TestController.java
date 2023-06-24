package com.healthtrack.calculator.controller;

import com.healthtrack.calculator.annotation.Warning;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@Warning(Warning.Type.DELETE_IN_FUTURE)
public class TestController {

    @GetMapping("/test")
    public String test(){
        log.info("handled request");
        return "123";
    }
}
