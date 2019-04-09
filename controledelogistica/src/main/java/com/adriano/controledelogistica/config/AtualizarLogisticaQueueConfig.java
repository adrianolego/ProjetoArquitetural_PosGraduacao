package com.adriano.controledelogistica.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarLogisticaQueueConfig {

    @Value("${config.atualizarLogistica.exchange}")
    private String exchange;
    @Value("${config.atualizarLogistica.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.atualizarLogistica.sendQueue}")
    private String atualizarLogistica;

    @Bean
    public DirectExchange exchangeAtualizarLogistica() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queueAtualizarLogistica() {
        return QueueBuilder.durable(atualizarLogistica)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueueAtualizarLogistica() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding bindingAtualizarLogistica() {
        return BindingBuilder.bind(queueAtualizarLogistica()).to(exchangeAtualizarLogistica()).with(atualizarLogistica);
    }
}
