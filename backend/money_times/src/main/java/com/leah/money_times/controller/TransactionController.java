package com.leah.money_times.controller;

import com.leah.money_times.model.Bill;
import com.leah.money_times.model.Transaction;
import com.leah.money_times.request.TransactionRequest;
import com.leah.money_times.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("{idUser}/transactions/bill")
    public ResponseEntity<TransactionRequest> createNewBill(@PathVariable String idUser, @RequestBody TransactionRequest transactionRequest){
        transactionService.createNewBIll(idUser, transactionRequest);
        return ResponseEntity.ok(transactionRequest);
    }
    @PostMapping("{idUser}/transactions/income")
    public ResponseEntity<TransactionRequest> createNewIncome(@PathVariable String idUser, @RequestBody TransactionRequest transactionRequest){
        transactionService.createNewIncome(idUser, transactionRequest);
        return ResponseEntity.ok(transactionRequest);
    }

}
