package com.itq.forjametalica.jms;

import org.springframework.jms.annotation.JmsListener;

public class JmsConsumer {
    
    @JmsListener(destination = "test.queue")
    public void receiveMessage(String message) {
        System.out.println("Mensaje desencolado: " + message);
    }


}
