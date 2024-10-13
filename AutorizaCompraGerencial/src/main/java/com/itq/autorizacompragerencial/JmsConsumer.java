package com.itq.autorizacompragerencial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.itq.autorizacompragerencial.dto.Purchase;


@Component
public class JmsConsumer {

    @Autowired
    private Purchase purchase;

    @Autowired
    private JmsProducer jmsProducer;

    @JmsListener(destination = "gerencial.in")
    public void receiveMessage(String message) {
        boolean authorized;
        authorized = Math.random() < 0.5;
        if(authorized) {
            System.out.println("La compra con id: " +purchase.getIdPurchase() +" fue autorizada.");
            jmsProducer.processAndSendMessage(message);
        } else
            System.out.println("La compra con id: " +purchase.getIdPurchase() +" fue rechazada.");
    }
}