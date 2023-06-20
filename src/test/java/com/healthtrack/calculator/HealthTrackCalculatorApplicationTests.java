package com.healthtrack.calculator;

import com.healthtrack.calculator.mapper.ItemMapper;
import com.healthtrack.calculator.service.TestService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HealthTrackCalculatorApplicationTests {

    @Resource
    private ItemMapper itemMapper;

    @Resource
    private TestService testService;

    @Test
    void contextLoads() {
        System.out.println(itemMapper.getById(154));
        System.out.println(5);
        testService.testAsync();
        System.out.println(6);
    }

}
