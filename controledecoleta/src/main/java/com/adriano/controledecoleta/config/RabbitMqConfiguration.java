package com.adriano.controledecoleta.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
@Profile({"default"})
@EnableRabbit
public class RabbitMqConfiguration implements RabbitListenerConfigurer {

    @Value("${config.mail.exchange}")
    private String mailExchange;
    @Value("${config.mail.sendDLQQueue}")
    private String sendMailDLQQueue;
    @Value("${config.mail.sendQueue}")
    private String sendMailQueue;

    @Value("${config.amq.hostname}")
    private String hostAmq;
    @Value("${config.amq.port}")
    private Integer portAmq;
    @Value("${config.amq.username}")
    private String userAmq;
    @Value("${config.amq.password}")
    private String passAmq;

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(hostAmq, portAmq);
        connectionFactory.setUsername(userAmq);
        connectionFactory.setPassword(passAmq);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public DirectExchange mailExchange() {
        return new DirectExchange(mailExchange, true, false);
    }

    @Bean
    public Queue sendMailQueue() {
        return QueueBuilder.durable(sendMailQueue)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", sendMailDLQQueue)
                .build();
    }

    @Bean
    public Queue sendMailDLQQueue() {
        return new Queue(sendMailDLQQueue, true, false, false);
    }

    @Bean
    public Binding sendMailBinding() {
        return BindingBuilder.bind(sendMailQueue()).to(mailExchange()).with(sendMailQueue);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
}
