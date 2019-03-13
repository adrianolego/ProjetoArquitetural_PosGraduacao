package com.adriano.controledefrete.component;

import com.adriano.controledefrete.model.Frete;
import com.adriano.controledefrete.component.sender.DadosFrete;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClienteEnvioMsg implements DadosFrete {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${config.mail.exchange}")
    private String mailExchange;
    @Value("${config.mail.sendQueue}")
    private String sendMailQueue;

    @Override
    public void enviarDadosFreteFila(Frete frete) {
        rabbitTemplate.convertAndSend(mailExchange, sendMailQueue, frete);
    }

}
