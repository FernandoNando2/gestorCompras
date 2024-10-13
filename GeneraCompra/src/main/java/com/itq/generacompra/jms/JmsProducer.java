package com.itq.generacompra.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import com.itq.generacompra.dto.Purchase;

@Component
public class JmsProducer {
    
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(Purchase purchase) {
        try {
            String purchaseJson = objectMapper.writeValueAsString(purchase);
            System.out.println("Enviando mensaje: " + purchaseJson);
            this.jmsMessagingTemplate.convertAndSend("operativo.in", purchaseJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}