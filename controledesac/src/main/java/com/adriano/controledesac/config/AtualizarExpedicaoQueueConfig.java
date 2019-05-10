package com.adriano.controledesac.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarExpedicaoQueueConfig {
    @Value("${config.atualizarExpedicao.exchange}")
    private String exchange;
    @Value("${config.atualizarExpedicao.sendDLQQueue}")
    private String dlqQueue;
    @Value("${config.atualizarExpedicao.sendQueue}")
    private String atualizarExpedicao;

    @Bean
    public DirectExchange exchangeAtualizarExpedicao() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    public Queue queueAtualizarExpedicao() {
        return QueueBuilder.durable(atualizarExpedicao)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", dlqQueue)
                .build();
    }

    @Bean
    public Queue dlqQueueAtualizarExpedicao() {
        return new Queue(dlqQueue, true, false, false);
    }

    @Bean
    public Binding bindingAtualizarExpedicao() {
        return BindingBuilder.bind(queueAtualizarExpedicao()).to(exchangeAtualizarExpedicao()).with(atualizarExpedicao);
    }
}
