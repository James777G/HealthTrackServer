package com.healthtrack.calculator.controller;

import com.healthtrack.calculator.annotation.Warning;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Warning(Warning.Type.DELETE_IN_FUTURE)
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "123";
    }
}
