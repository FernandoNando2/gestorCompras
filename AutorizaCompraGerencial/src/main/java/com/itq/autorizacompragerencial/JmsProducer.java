package com.itq.autorizacompragerencial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void processAndSendMessage(String message) {
        System.out.println("Procesando mensaje: " + message);
        jmsMessagingTemplate.convertAndSend("gerencial.out", message);
    }
}