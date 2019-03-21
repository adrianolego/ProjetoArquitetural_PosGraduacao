package com.adriano.controledecoleta.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncomendaQueueConfig {
    @Value("${config.encomenda.exchange}")
    private String exchange;
    @Value("${config.encomenda.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.encomenda.sendQueue}")
    private String queueEncomenda;

    @Bean
    public DirectExchange exchangeEncomenda() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queueEncomenda() {
        return QueueBuilder.durable(queueEncomenda)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueueEncomenda() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding bindingEncomenda() {
        return BindingBuilder.bind(queueEncomenda()).to(exchangeEncomenda()).with(queueEncomenda);
    }
}
