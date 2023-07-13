package com.healthtrack.calculator.service.message;

import com.healthtrack.calculator.config.mq.RabbitInputRoutineBinding;
import com.healthtrack.calculator.domain.UserInfo;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

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
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);

        System.out.println("Sending Message: " + message);
        Message msg = new Message(message.getBytes(), messageProperties);
        Object response = amqpTemplate.convertSendAndReceive(inputBinding.getExchangeName(), inputBinding.getRoutineKey(), msg);

        String messageBody = null;

        // if the response is of format byte[]
        if (response instanceof byte[]) {
            messageBody = new String((byte[]) response, StandardCharsets.UTF_8);
        }
        System.out.println("Response: " + messageBody);
        return messageBody;
    }

}
