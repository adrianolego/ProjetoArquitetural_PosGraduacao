package com.adriano.controledefaturamento.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistrarFaturamentoQueueConfig {
    @Value("${config.registrarFaturamento.exchange}")
    private String exchange;
    @Value("${config.registrarFaturamento.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.registrarFaturamento.sendQueue}")
    private String queueRegistrarFaturamento;

    @Bean
    public DirectExchange exchangeRegistrarFaturamento() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queueRegistrarFaturamento() {
        return QueueBuilder.durable(queueRegistrarFaturamento)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueueRegistrarFaturamento() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding bindingRegistrarFaturamento() {
        return BindingBuilder.bind(queueRegistrarFaturamento()).to(exchangeRegistrarFaturamento()).with(queueRegistrarFaturamento);
    }
}
