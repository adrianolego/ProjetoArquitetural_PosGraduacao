package com.adriano.controledefrete.component.consumer;

import com.adriano.controledefrete.model.Frete;
import com.adriano.controledefrete.service.FreteService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class EncomendaConsumer {

    @Autowired
    private FreteService mailService;

    @RabbitListener(queues = "${config.mail.sendQueue}", containerFactory = "rabbitListenerContainerFactory")
    public void sendMail(final Frete frete) throws Exception {
        try {
            mailService.sendFreteMail(frete);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
