package com.healthtrack.calculator.config.mq;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class configures rabbitmq actual setup based on {@link RabbitInputRoutineBinding}
 */
@Slf4j
@Configuration
public class RabbitMessageQueueConfig {

    @Resource
    private RabbitInputRoutineBinding inputBinding;

    @Bean
    public Queue algorithmInputQueue(){
        log.info("Initialization of Message Queue");
        return new Queue(inputBinding.getQueueName());
    }

    @Bean
    public DirectExchange directExchange(){
        log.info("Initialization of Message Exchange");
        return new DirectExchange(inputBinding.getExchangeName());
    }

    @Bean
    public Binding exchangeToQueueBinding(){
        log.info("Initialization of Queue to Exchange Binding");
        return BindingBuilder.bind(algorithmInputQueue()).to(directExchange()).with(inputBinding.getRoutineKey());
    }

}
