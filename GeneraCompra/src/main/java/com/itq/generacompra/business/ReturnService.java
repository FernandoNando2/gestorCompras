package com.itq.generacompra.business;

import com.itq.generacompra.dto.Return;
import com.itq.generacompra.dto.UpdateReturn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ReturnService {
    private Map<Integer, Return> returns = new HashMap<>();
    private AtomicInteger idCounter = new AtomicInteger();

    public Return createReturn(Return devolucion) {
        int idReturn = idCounter.incrementAndGet();
        devolucion.setIdReturn(idReturn);
        returns.put(idReturn, devolucion);
        return devolucion;
    }

    public Return getReturnById(int idReturn) {
        return returns.get(idReturn);
    }

    public Return updateReturn(UpdateReturn updateReturn) {
        Return devolucion = returns.get(updateReturn.getIdReturn());
        devolucion.setStatus(updateReturn.getStatus());
        return devolucion;
    }

    public List<Return> getAllReturns() {
        return returns.values().stream().collect(Collectors.toList());
    }

    public List<Return> getReturnsByReturnDate(String returnDate) {
        return returns.values().stream().filter(r -> r.getReturnDate().equals(returnDate)).collect(Collectors.toList());
    }

    public void deleteReturn(int idReturn) {
        returns.remove(idReturn);
    }

}