package com.itq.autorizacompragerencial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itq.autorizacompragerencial.dto.Purchase;


@Component
public class JmsConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JmsProducer jmsProducer;

    @JmsListener(destination = "gerencial.in")
    public void receiveMessage(String message) {
        boolean authorized;
        try{
            Purchase purchase = objectMapper.readValue(message, Purchase.class);
            authorized = Math.random() < 0.5;
            if(authorized) {
                System.out.println("La compra con id: " +purchase.getIdPurchase() +" fue autorizada.");
                jmsProducer.processAndSendMessage(message);
            } else
                System.out.println("La compra con id: " +purchase.getIdPurchase() +" fue rechazada.");
        } catch (Exception e) {
            System.out.println("Error al procesar mensaje: " + message);
        }
        
    }
}