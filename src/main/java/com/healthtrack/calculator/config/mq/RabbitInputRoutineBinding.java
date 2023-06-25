package com.healthtrack.calculator.config.mq;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * This class creates a bean consisting all the necessary information for setting up
 * message queue, and is independent of any functionalities
 */
@Data
@Component("inputBinding")
@PropertySource("classpath:mq/RabbitMQ.properties")
public class RabbitInputRoutineBinding {

    @Value("${QUEUE_NAME}")
    private String queueName;

    @Value("${EXCHANGE_NAME}")
    private String exchangeName;

    @Value("${ROUTINE_KEY}")
    private String routineKey;


}
