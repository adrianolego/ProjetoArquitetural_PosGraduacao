package com.adriano.controledefrete.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogisticaQueueConfig {
    @Value("${config.logistica.exchange}")
    private String exchange;
    @Value("${config.logistica.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.logistica.sendQueue}")
    private String queueLogistica;

    @Bean
    public DirectExchange exchangeLogistica() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queueLogistica() {
        return QueueBuilder.durable(queueLogistica)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueueLogistica() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding bindingLogistica() {
        return BindingBuilder.bind(queueLogistica()).to(exchangeLogistica()).with(queueLogistica);
    }
}
