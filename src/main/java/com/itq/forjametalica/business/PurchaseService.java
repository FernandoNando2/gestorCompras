package com.itq.forjametalica.business;

import com.itq.forjametalica.dto.Purchase;
import com.itq.forjametalica.dto.UpdatePurchase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private Map<Integer, Purchase> pusrchases = new HashMap<>();
    private AtomicInteger idCounter = new AtomicInteger();

    public Purchase createPurchase(Purchase pusrchase) {
        int idPurchase = idCounter.incrementAndGet();
        pusrchase.setIdPurchase(idPurchase);
        pusrchases.put(idPurchase, pusrchase);
        return pusrchase;
    }

    public Purchase getPurchaseById(int idPurchase) {
        return pusrchases.get(idPurchase);
    }

    public List<Purchase> getAllPurchases() {
        return pusrchases.values().stream().collect(Collectors.toList());
    }

    public List<Purchase> getPurchasesByOrderDate(String orderDate) {
        return pusrchases.values().stream().filter(p -> p.getOrderDate().equals(orderDate)).collect(Collectors.toList());
    }

    public Purchase updatePurchase(UpdatePurchase updatePusrchase) {
        Purchase pusrchase = pusrchases.get(updatePusrchase.getIdPurchase());
        pusrchase.setDeliveryDate(updatePusrchase.getDeliveryDate());
        pusrchase.setStatus(updatePusrchase.getStatus());
        pusrchase.setProblem(updatePusrchase.getProblem());
        return pusrchase;
    }

    public void deletePurchase(int idPurchase) {
        pusrchases.remove(idPurchase);
    }

}