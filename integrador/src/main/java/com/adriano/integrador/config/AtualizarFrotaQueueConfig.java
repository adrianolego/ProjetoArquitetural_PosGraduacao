package com.adriano.integrador.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarFrotaQueueConfig {
    @Value("${config.registrarFaturamento.exchange}")
    private String exchange;
    @Value("${config.registrarFaturamento.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.registrarFaturamento.sendQueue}")
    private String queueFaturamento;

    @Bean
    public DirectExchange exchangeFaturamento() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queueFaturamento() {
        return QueueBuilder.durable(queueFaturamento)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueueFaturamento() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding bindingFaturamento() {
        return BindingBuilder.bind(queueFaturamento()).to(exchangeFaturamento()).with(queueFaturamento);
    }
}
