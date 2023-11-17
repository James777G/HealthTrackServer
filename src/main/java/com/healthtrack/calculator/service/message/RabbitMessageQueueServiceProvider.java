package com.healthtrack.calculator.service.message;

import com.healthtrack.calculator.config.mq.RabbitInputRoutineBinding;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
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
    public Object sendAndReceive(String message) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentEncoding("UTF-8");
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);

        Message msg = new Message(message.getBytes(), messageProperties);
        log.info("RPC INVOCATION TO RABBITMQ");
        Object response = amqpTemplate.convertSendAndReceive(inputBinding.getExchangeName(), inputBinding.getRoutineKey(), msg);
        if (response instanceof byte[]) {
            return new String((byte[]) response, StandardCharsets.UTF_8);
        }
        return response;
    }
}
