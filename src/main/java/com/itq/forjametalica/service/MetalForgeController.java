package com.itq.forjametalica.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itq.forjametalica.business.PurchaseService;
import com.itq.forjametalica.business.ReturnService;
import com.itq.forjametalica.dto.Purchase;
import com.itq.forjametalica.dto.UpdatePurchase;
import com.itq.forjametalica.dto.Return;
import com.itq.forjametalica.dto.UpdateReturn;
import com.itq.forjametalica.dto.ResponseCode;

@RestController
@RequestMapping("/metalforge")
public class MetalForgeController {

    private ReturnService returnService;
    private PurchaseService purchaseService;
    
    public MetalForgeController(ReturnService returnService, PurchaseService purchaseService) {
        this.returnService = returnService;
        this.purchaseService = purchaseService;
    }

    @PostMapping(value = "/purchase", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createPurchase(@RequestBody Purchase purchase) {
        Purchase createdPurchase = purchaseService.createPurchase(purchase);
        return new ResponseEntity<>(createdPurchase,HttpStatus.CREATED);
    }

    @GetMapping("/purchase")
    public ResponseEntity<?> getPurchases(@RequestParam(required = false) Integer idPurchase) {
        if (idPurchase != null) {
            Purchase purchase = purchaseService.getPurchaseById(idPurchase);
            if (purchase == null) {
                ResponseCode responseCode = new ResponseCode();
                responseCode.setCode("404");
                responseCode.setMensaje("La compra no fue encontrada.");
                return new ResponseEntity<>(responseCode, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(purchase, HttpStatus.OK);
        } else {
            List<Purchase> purchases = purchaseService.getAllPurchases();
            return new ResponseEntity<>(purchases, HttpStatus.OK);
        }
    }

    @PutMapping("/purchase")
    public ResponseEntity<?> updatePurchase(@RequestBody UpdatePurchase updatePurchase) {
        Purchase purchase = purchaseService.updatePurchase(updatePurchase);
        if (purchase == null) {
            ResponseCode responseCode = new ResponseCode();
            responseCode.setCode("404");
            responseCode.setMensaje("La compra no fue encontrada.");
            return new ResponseEntity<>(responseCode, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(purchase, HttpStatus.OK);
    }

    @DeleteMapping("/purchase")
    public ResponseEntity<?> deletePurchase(@RequestParam int idPurchase) {
        Purchase purchase = purchaseService.getPurchaseById(idPurchase);
        ResponseCode responseCode = new ResponseCode();
        if (purchase == null) {
            responseCode.setCode("404");
            responseCode.setMensaje("La compra no fue encontrada.");
            return new ResponseEntity<>(responseCode, HttpStatus.NOT_FOUND);
        }
        purchaseService.deletePurchase(idPurchase);
        responseCode.setCode("200");
        responseCode.setMensaje("La compra fue eliminada correctamente.");
        return new ResponseEntity<>(responseCode, HttpStatus.OK);
    }

    @PostMapping(value = "/return", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createReturn(@RequestBody Return devolucion) {
        Return createdReturn = returnService.createReturn(devolucion);
        return new ResponseEntity<>(createdReturn,HttpStatus.CREATED);
    }

    @GetMapping("/return")
    public ResponseEntity<?> getReturns(@RequestParam(required = false) Integer idReturn) {
        if (idReturn != null) {
            Return devolucion = returnService.getReturnById(idReturn);
            if (devolucion == null) {
                ResponseCode responseCode = new ResponseCode();
                responseCode.setCode("404");
                responseCode.setMensaje("La devolución no fue encontrada.");
                return new ResponseEntity<>(responseCode,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(devolucion,HttpStatus.OK);
        } else {
            List<Return> Returns = returnService.getAllReturns();
            return new ResponseEntity<>(Returns,HttpStatus.OK);
        }
    }

    @PutMapping("/return")
    public ResponseEntity<?> updateReturn(@RequestBody UpdateReturn updateReturn) {
        Return devolucion = returnService.updateReturn(updateReturn);
        if (devolucion == null) {
            ResponseCode responseCode = new ResponseCode();
            responseCode.setCode("404");
            responseCode.setMensaje("La devolución no fue encontrada.");
            return new ResponseEntity<>(responseCode,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(devolucion,HttpStatus.OK);
    }

    @DeleteMapping("/return")
    public ResponseEntity<?> deleteReturn(@RequestParam Integer idReturn) {
        ResponseCode responseCode = new ResponseCode();
        Return devolucion = returnService.getReturnById(idReturn);
        if (devolucion == null) {
            responseCode.setCode("404");
            responseCode.setMensaje("La devolución no fue encontrada.");
            return new ResponseEntity<>(responseCode,HttpStatus.NOT_FOUND);
        }
        returnService.deleteReturn(idReturn);
        responseCode.setCode("200");
        responseCode.setMensaje("La devolución fue eliminada correctamente.");
        return new ResponseEntity<>(responseCode,HttpStatus.OK);
    }

}
