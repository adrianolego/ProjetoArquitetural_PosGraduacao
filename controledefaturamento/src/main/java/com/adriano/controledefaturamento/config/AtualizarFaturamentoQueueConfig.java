package com.adriano.controledefaturamento.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarFaturamentoQueueConfig {
    @Value("${config.atualizarFaturamento.exchange}")
    private String exchange;
    @Value("${config.atualizarFaturamento.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.atualizarFaturamento.sendQueue}")
    private String queueAtualizarFaturamento;

    @Bean
    public DirectExchange exchangeAtualizarFaturamento() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queueAtualizarFaturamento() {
        return QueueBuilder.durable(queueAtualizarFaturamento)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueueAtualizarFaturamento() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding bindingAtualizarFaturamento() {
        return BindingBuilder.bind(queueAtualizarFaturamento()).to(exchangeAtualizarFaturamento()).with(queueAtualizarFaturamento);
    }
}
