package com.adriano.controledefrota.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarFreteQueueConfig {

    @Value("${config.atualizarFrete.exchange}")
    private String exchange;
    @Value("${config.atualizarFrete.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.atualizarFrete.sendQueue}")
    private String atualizarFrete;

    @Bean
    public DirectExchange exchangeAtualizarFrete() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queueAtualizarFrete() {
        return QueueBuilder.durable(atualizarFrete)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueueAtualizarFrete() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding bindingAtualizarFrete() {
        return BindingBuilder.bind(queueAtualizarFrete()).to(exchangeAtualizarFrete()).with(atualizarFrete);
    }
}
