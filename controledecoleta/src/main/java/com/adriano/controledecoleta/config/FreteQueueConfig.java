package com.adriano.controledecoleta.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FreteQueueConfig {

    @Value("${config.calcularFrete.exchange}")
    private String exchange;
    @Value("${config.calcularFrete.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.calcularFrete.sendQueue}")
    private String queueCalcularFrete;

    @Bean
    public DirectExchange exchangeFrete() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queueFrete() {
        return QueueBuilder.durable(queueCalcularFrete)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueueFrete() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding bindingFrete() {
        return BindingBuilder.bind(queueFrete()).to(exchangeFrete()).with(queueCalcularFrete);
    }
}
