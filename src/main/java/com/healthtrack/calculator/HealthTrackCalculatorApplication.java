package com.healthtrack.calculator;

import com.healthtrack.calculator.config.ThreadPoolConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class HealthTrackCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthTrackCalculatorApplication.class, args);
    }

}
