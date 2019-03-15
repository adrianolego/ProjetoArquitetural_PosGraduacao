package com.adriano.controledecoleta.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FreteQueueConfig {

    @Value("${config.frete.exchange}")
    private String exchange;
    @Value("${config.frete.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.frete.sendQueue}")
    private String queue;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(queue)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueue() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(queue);
    }
}
