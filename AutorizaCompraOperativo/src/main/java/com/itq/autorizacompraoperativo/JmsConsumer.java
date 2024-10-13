package com.itq.autorizacompraoperativo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itq.autorizacompraoperativo.dto.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    @Autowired
    private JmsProducer jmsProducer;

    @Autowired
    private ObjectMapper objectMapper;

    @JmsListener(destination = "operativo.in")
    public void receiveMessage(String message) {
        boolean authorized;
        try {
            Purchase purchase = objectMapper.readValue(message, Purchase.class);
            System.out.println("Mensaje desencolado: " + message);
            if(purchase.getTotalPrice() >= 100000 || purchase.isUrgent()) {
                jmsProducer.processAndSendMessage(message, "gerencial.in");
            } else {
                authorized = Math.random() < 0.5;
                if (authorized){
                    System.out.println("La compra con id: " +purchase.getIdPurchase() +" fue autorizada.");
                    jmsProducer.processAndSendMessage(message,"operativo.out");
                }else
                    System.out.println("La compra con id: " +purchase.getIdPurchase() +" fue rechazada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}