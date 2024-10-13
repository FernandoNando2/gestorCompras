package com.itq.generacompra.business;

import com.itq.generacompra.dto.Purchase;
import com.itq.generacompra.dto.UpdatePurchase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private Map<Integer, Purchase> purchases = new HashMap<>();
    private AtomicInteger idCounter = new AtomicInteger();

    public Purchase createPurchase(Purchase purchase) {
        int idPurchase = idCounter.incrementAndGet();
        purchase.setIdPurchase(idPurchase);
        purchases.put(idPurchase, purchase);
        return purchase;
    }

    public Purchase getPurchaseById(int idPurchase) {
        return purchases.get(idPurchase);
    }

    public List<Purchase> getAllPurchases() {
        return purchases.values().stream().collect(Collectors.toList());
    }

    public List<Purchase> getPurchasesByOrderDate(String orderDate) {
        return purchases.values().stream().filter(p -> p.getOrderDate().equals(orderDate)).collect(Collectors.toList());
    }

    public Purchase updatePurchase(UpdatePurchase updatepurchase) {
        Purchase purchase = purchases.get(updatepurchase.getIdPurchase());
        purchase.setDeliveryDate(updatepurchase.getDeliveryDate());
        purchase.setStatus(updatepurchase.getStatus());
        purchase.setProblem(updatepurchase.getProblem());
        return purchase;
    }

    public void deletePurchase(int idPurchase) {
        purchases.remove(idPurchase);
    }

}