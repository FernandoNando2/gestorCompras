package com.itq.forjametalica.dto;

public class UpdatePurchase {
    private int idPurchase;
    private String deliveryDate;
    private Purchase.Status status;
    private String problem;

    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Purchase.Status getStatus() {
        return status;
    }

    public void setStatus(Purchase.Status status) {
        this.status = status;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

}
