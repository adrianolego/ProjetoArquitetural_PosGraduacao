package com.adriano.controledesac.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncomendaQueueConfig {
    @Value("${config.registrarEncomenda.exchange}")
    private String exchange;
    @Value("${config.registrarEncomenda.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.registrarEncomenda.sendQueue}")
    private String registrarEncomenda;

    @Bean
    public DirectExchange exchangeRegistrarEncomenda() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queueRegistrarEncomenda() {
        return QueueBuilder.durable(registrarEncomenda)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueueRegistrarEncomenda() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding bindingRegistrarEncomenda() {
        return BindingBuilder.bind(queueRegistrarEncomenda()).to(exchangeRegistrarEncomenda()).with(registrarEncomenda);
    }
}
