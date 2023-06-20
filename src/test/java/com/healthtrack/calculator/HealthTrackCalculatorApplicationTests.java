package com.healthtrack.calculator;

import com.healthtrack.calculator.mapper.ItemMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HealthTrackCalculatorApplicationTests {

    @Resource
    private ItemMapper itemMapper;

    @Test
    void contextLoads() {
        System.out.println(itemMapper.getById(154));
    }

}
