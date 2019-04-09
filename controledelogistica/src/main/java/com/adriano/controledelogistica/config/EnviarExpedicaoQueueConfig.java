package com.adriano.controledelogistica.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnviarExpedicaoQueueConfig {

    @Value("${config.enviarExpedicao.exchange}")
    private String exchange;
    @Value("${config.enviarExpedicao.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.enviarExpedicao.sendQueue}")
    private String enviarExpedicao;

    @Bean
    public DirectExchange exchangeEnviarExpedicao() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queueEnviarExpedicao() {
        return QueueBuilder.durable(enviarExpedicao)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueueEnviarExpedicao() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding bindingEnviarExpedicao() {
        return BindingBuilder.bind(queueEnviarExpedicao()).to(exchangeEnviarExpedicao()).with(enviarExpedicao);
    }
}
