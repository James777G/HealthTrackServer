package com.healthtrack.calculator.service.message;

import com.healthtrack.calculator.config.mq.RabbitInputRoutineBinding;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service("messageService")
public class RabbitMessageQueueServiceProvider implements MessageService{

    @Resource
    private AmqpTemplate amqpTemplate;

    @Resource
    private RabbitInputRoutineBinding inputBinding;

    @Override
    public void sendMessage(Object message) {
        amqpTemplate.convertAndSend(inputBinding.getExchangeName(), inputBinding.getRoutineKey(), message);
    }

    @Override
    public Object sendAndReceive(Object message) {
        return amqpTemplate.convertSendAndReceive(inputBinding.getExchangeName(), inputBinding.getRoutineKey(), message);
    }
}
