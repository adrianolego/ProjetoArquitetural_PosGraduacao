package com.adriano.controledelogistica.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalcularFreteQueueConfig {
    @Value("${config.calcularFrete.exchange}")
    private String exchange;
    @Value("${config.calcularFrete.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.calcularFrete.sendQueue}")
    private String calcularFrete;

    @Bean
    public DirectExchange exchangeCalcularFrete() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queueCalcularFrete() {
        return QueueBuilder.durable(calcularFrete)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueueCalcularFrete() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding bindingCalcularFrete() {
        return BindingBuilder.bind(queueCalcularFrete()).to(exchangeCalcularFrete()).with(calcularFrete);
    }
}
