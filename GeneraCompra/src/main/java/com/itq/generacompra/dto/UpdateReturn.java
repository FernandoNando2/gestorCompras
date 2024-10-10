package com.itq.generacompra.dto;

public class UpdateReturn {

    private int idReturn;
    private Return.Status status;

    public int getIdReturn() {
        return idReturn;
    }

    public void setIdReturn(int idReturn) {
        this.idReturn = idReturn;
    }

    public Return.Status getStatus() {
        return status;
    }

    public void setStatus(Return.Status status) {
        this.status = status;
    }

}
