package com.healthtrack.calculator.service.message;

import jakarta.annotation.Resource;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service("messageService")
public class RabbitMessageQueueServiceProvider implements MessageService{

    @Resource
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(Object message) {
        amqpTemplate.convertAndSend("direct_exchange", "algorithm_routine", message);
    }
}
