package com.adriano.controledefrota.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnviarLogisticaQueueConfig {
    @Value("${config.enviarLogistica.exchange}")
    private String exchange;
    @Value("${config.enviarLogistica.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.enviarLogistica.sendQueue}")
    private String enviarLogistica;

    @Bean
    public DirectExchange exchangeEnviarLogistica() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queueEnviarLogistica() {
        return QueueBuilder.durable(enviarLogistica)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueueEnviarLogistica() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding bindingEnviarLogistica() {
        return BindingBuilder.bind(queueEnviarLogistica()).to(exchangeEnviarLogistica()).with(enviarLogistica);
    }
}
