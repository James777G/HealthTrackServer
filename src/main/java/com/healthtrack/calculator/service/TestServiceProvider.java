package com.healthtrack.calculator.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component("testService")
public class TestServiceProvider implements TestService{

    @Override
    @Async("asyncServiceExecutor")
    public void testAsync() {
        System.out.println("sleep start");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
