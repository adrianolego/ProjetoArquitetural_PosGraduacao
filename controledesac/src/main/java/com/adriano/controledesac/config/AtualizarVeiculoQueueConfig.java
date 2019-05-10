package com.adriano.controledesac.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarVeiculoQueueConfig {

    @Value("${config.atualizarVeiculo.exchange}")
    private String exchange;
    @Value("${config.atualizarVeiculo.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.atualizarVeiculo.sendQueue}")
    private String atualizarVeiculo;

    @Bean
    public DirectExchange exchangeAtualizarVeiculo() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queueAtualizarVeiculo() {
        return QueueBuilder.durable(atualizarVeiculo)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueueAtualizarVeiculo() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding bindingAtualizarVeiculo() {
        return BindingBuilder.bind(queueAtualizarVeiculo()).to(exchangeAtualizarVeiculo()).with(atualizarVeiculo);
    }
}
