package com.adriano.controledefrete.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgendarVeiculoQueueConfig {
    @Value("${config.enviarFrota.exchange}")
    private String exchange;
    @Value("${config.enviarFrota.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.enviarFrota.sendQueue}")
    private String queueFrota;

    @Bean
    public DirectExchange exchangeFrota() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queueFrota() {
        return QueueBuilder.durable(queueFrota)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueueFrota() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding bindingFrota() {
        return BindingBuilder.bind(queueFrota()).to(exchangeFrota()).with(queueFrota);
    }
}
