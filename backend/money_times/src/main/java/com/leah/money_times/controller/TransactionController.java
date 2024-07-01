package com.leah.money_times.controller;

import com.leah.money_times.model.Bill;
import com.leah.money_times.model.Transaction;
import com.leah.money_times.request.TransactionRequest;
import com.leah.money_times.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("{idUser}/transactions/{idTransaction}")
    public ResponseEntity<TransactionRequest> updateTransaction(@PathVariable String idUser, @PathVariable String idTransaction, @RequestBody TransactionRequest transactionRequest){
        transactionService.updateTransactionInfo(idUser,idTransaction,transactionRequest);
        return ResponseEntity.ok(transactionRequest);
    }
    @DeleteMapping("{idUser}/transactions/{idTransaction}")
    public ResponseEntity<String> deleteTransactionById(@PathVariable String idUser, @PathVariable String idTransaction){
        transactionService.deleteTransactionById(idUser,idTransaction);
        return ResponseEntity.ok("Transaction sucessfully deleted");
    }

}
