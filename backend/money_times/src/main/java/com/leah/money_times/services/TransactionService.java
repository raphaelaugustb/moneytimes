package com.leah.money_times.services;

import com.leah.money_times.entity.User;
import com.leah.money_times.model.Bill;
import com.leah.money_times.model.Income;
import com.leah.money_times.model.Transaction;
import com.leah.money_times.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  UserService userService;

    //Verificar transação
    public Transaction verifyTransaction(Transaction transaction) {
        if (transaction == null){
            throw  new NullPointerException("Transação inválida");
        } else {
            return transaction;
        }
    }
    public void createNewBIll(String userId , Bill bill){
        User user = userService.getUserById(userId);
        verifyTransaction(bill);
        bill.setTypeBill_Income("Bill");
        user.getTransactionList().add(bill);
        user.getBillsList().add(bill);
        userRepository.save(user);
    }
    public void createNewIncome(String userId, Income income){
        User user = userService.getUserById(userId);
        verifyTransaction(income);
        user.getTransactionList().add(income);
        user.getIncomesList().add(income);
        userRepository.save(user);
    }
    public List<Transaction> listAllTransactions(String userId) {
        List<Transaction> transactionList = userService.getUserById(userId).getTransactionList();
        if (transactionList.isEmpty() || transactionList == null)  {
            throw  new NullPointerException("Lista está vazia");
        }
        return transactionList;
    }
}
