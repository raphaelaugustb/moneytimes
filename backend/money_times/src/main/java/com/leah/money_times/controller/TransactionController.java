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

    @GetMapping("{idUser}/transactions/{name_type_query}/{name_type_transaction}")
    public ResponseEntity<List<Transaction>> listTransactionsByNameOrType(@PathVariable String idUser, @PathVariable String name_type_query, @PathVariable String name_type_transaction) {
        if (name_type_query.equals("name")) {
            return ResponseEntity.ok(transactionService.listTransactionByName(idUser, name_type_transaction));
        } else if (name_type_query.equals("type")) {
            return ResponseEntity.ok(transactionService.listTransactionByType(idUser, name_type_transaction));
        } else {
            return ResponseEntity.ok(transactionService.listAllTransactions(idUser));
        }
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

    @GetMapping("{idUser}/transactions/value/{initValue}/{finalValue}")
    public ResponseEntity<List<Transaction>> listTransactionsByValue(@PathVariable String idUser, @PathVariable String initValue, @PathVariable String finalValue) {
        int finalValuetoInt = Integer.parseInt(finalValue);
        int initialValuteToInt = Integer.parseInt(initValue);
        return ResponseEntity.ok(transactionService.listTransactionByValue(idUser, initialValuteToInt, finalValuetoInt));
    }

}
