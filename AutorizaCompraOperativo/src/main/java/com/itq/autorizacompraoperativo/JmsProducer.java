package com.itq.autorizacompraoperativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void processAndSendMessage(String message, String queue) {
        System.out.println("Procesando mensaje: " + message);
        jmsMessagingTemplate.convertAndSend(queue, message);
    }
}