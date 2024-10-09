package com.itq.forjametalica.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;

public class JmsProducer {
    
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(String message) {
        this.jmsMessagingTemplate.convertAndSend("test.queue", message);
    }

}
