package com.leah.money_times.controller;

import com.leah.money_times.model.Transaction;
import com.leah.money_times.request.TransactionRequest;
import com.leah.money_times.services.TransactionService;
import com.leah.money_times.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    private UserService userService;

    @PostMapping("{idUser}/transactions/bill")
    public ResponseEntity<TransactionRequest> createNewBill(@PathVariable String idUser, @RequestBody TransactionRequest transactionRequest) {
        transactionService.createNewBIll(idUser, transactionRequest);
        return ResponseEntity.ok(transactionRequest);
    }

    @PostMapping("{idUser}/transactions/income")
    public ResponseEntity<TransactionRequest> createNewIncome(@PathVariable String idUser, @RequestBody TransactionRequest transactionRequest) {
        transactionService.createNewIncome(idUser, transactionRequest);
        return ResponseEntity.ok(transactionRequest);
    }

    @PutMapping("{idUser}/transactions/{idTransaction}")
    public ResponseEntity<TransactionRequest> updateTransaction(@PathVariable String idUser, @PathVariable String idTransaction, @RequestBody TransactionRequest transactionRequest) {
        transactionService.updateTransactionInfo(idUser, idTransaction, transactionRequest);
        return ResponseEntity.ok(transactionRequest);
    }

    @DeleteMapping("{idUser}/transactions/{idTransaction}")
    public ResponseEntity<String> deleteTransactionById(@PathVariable String idUser, @PathVariable String idTransaction) {
        transactionService.deleteTransactionById(idUser, idTransaction);
        return ResponseEntity.ok("Transaction sucessfully deleted");
    }

    @GetMapping("{idUser}/transactions/")
    public ResponseEntity<List<Transaction>> listTransactionsByNameOrType(@PathVariable String idUser, @RequestParam String query, @RequestParam String type_name) {
        if (query.equals("name")) {
            return ResponseEntity.ok(transactionService.listTransactionByName(idUser, type_name));
        } else if (query.equals("type")) {
            return ResponseEntity.ok(transactionService.listTransactionByType(idUser, type_name));
        }
        return ResponseEntity.ok(transactionService.listAllTransactions(idUser));
    }

    @GetMapping("{idUser}/transactions/{typeQuery}")
    public ResponseEntity<List> listTransactionsByQueryType(@PathVariable String idUser, @PathVariable String typeQuery) {
        if (typeQuery.equals("income")) {
            return ResponseEntity.ok(transactionService.listAllIncomes(idUser));
        } else if (typeQuery.equals("bills")) {
            return ResponseEntity.ok(transactionService.listAllBills(idUser));
        } else {
            return ResponseEntity.ok(transactionService.listAllTransactions(idUser));
        }
    }

    @GetMapping("{idUser}/transactions/value")
    public ResponseEntity<List<Transaction>> listTransactionsByValue(@PathVariable String idUser, @RequestParam int initVal, @RequestParam int finalVal) {
        return ResponseEntity.ok(transactionService.listTransactionByValue(idUser, initVal, finalVal));
    }

}
