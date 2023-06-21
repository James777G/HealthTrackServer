package com.healthtrack.calculator.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@Configuration
@PropertySource("classpath:mq/RabbitMQ.properties")
public class RabbitMessageQueueConfig {

    @Value("${QUEUE_NAME}")
    private String queueName;

    @Value("${EXCHANGE_NAME}")
    private String exchangeName;

    @Value("${ROUTINE_KEY}")
    private String routineKey;

    @Bean
    public Queue algorithmInputQueue(){
        log.info("Initialization of Message Queue");
        return new Queue(queueName);
    }

    @Bean
    public DirectExchange directExchange(){
        log.info("Initialization of Message Exchange");
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding exchangeToQueueBinding(){
        log.info("Initialization of Queue to Exchange Binding");
        return BindingBuilder.bind(algorithmInputQueue()).to(directExchange()).with(routineKey);
    }

}
