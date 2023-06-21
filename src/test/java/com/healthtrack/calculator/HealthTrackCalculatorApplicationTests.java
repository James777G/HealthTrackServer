package com.healthtrack.calculator;

import com.healthtrack.calculator.mapper.ItemMapper;
import com.healthtrack.calculator.service.TestService;
import com.healthtrack.calculator.service.message.MessageService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HealthTrackCalculatorApplicationTests {

    @Resource
    private ItemMapper itemMapper;

    @Resource
    private TestService testService;

    @Resource
    private MessageService messageService;

    @Test
    void contextLoads() {
        messageService.sendMessage(666);
    }

}
