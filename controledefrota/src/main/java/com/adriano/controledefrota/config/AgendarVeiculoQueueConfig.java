package com.adriano.controledefrota.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgendarVeiculoQueueConfig {

    @Value("${config.agendarVeiculo.exchange}")
    private String exchange;
    @Value("${config.agendarVeiculo.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.agendarVeiculo.sendQueue}")
    private String agendarVeiculo;

    @Bean
    public DirectExchange exchangeAgendarVeiculo() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queueAgendarVeiculo() {
        return QueueBuilder.durable(agendarVeiculo)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueueAgendarVeiculo() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding bindingAgendarVeiculo() {
        return BindingBuilder.bind(queueAgendarVeiculo()).to(exchangeAgendarVeiculo()).with(agendarVeiculo);
    }
}
