package com.leah.money_times.services;

import com.leah.money_times.entity.User;
import com.leah.money_times.model.Bill;
import com.leah.money_times.model.Income;
import com.leah.money_times.model.Transaction;
import com.leah.money_times.repository.UserRepository;
import com.leah.money_times.request.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class TransactionService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  UserService userService;

    //Verificar transação
    public Boolean verifyTransaction(TransactionRequest transactionRequest) {
        if (transactionRequest != null){
            return true;
        } else {
            throw  new NullPointerException("Transação inválida");
        }
    }

    public void createNewBIll(String userId , TransactionRequest transactionRequest){
        User user = userService.verifyUser(userId);
        Bill bill = new Bill();
        Boolean verifiedTransaction = verifyTransaction(transactionRequest);
        if (verifiedTransaction){
            bill.setNameTransaction(transactionRequest.getNameTransaction());
            bill.setValueTransaction(transactionRequest.getValueTransaction());
            bill.setTypeTransaction(transactionRequest.getTypeTransaction());
            bill.setTypeBill_Income("Bill");
            user.setBalance(user.getBalance() - bill.getValueTransaction());
            user.getTransactionList().add(bill);
            user.getBillsList().add(bill);
            userRepository.save(user);
        }
    }
    public void createNewIncome(String userId, TransactionRequest transactionRequest){
        User user = userService.verifyUser(userId);
        boolean verifiedTransaction =verifyTransaction(transactionRequest);
        Income income = new Income();
        if (verifiedTransaction){
            income.setNameTransaction(transactionRequest.getNameTransaction());
            income.setValueTransaction(transactionRequest.getValueTransaction());
            income.setTypeTransaction(transactionRequest.getTypeTransaction());
            income.setTypeBill_Income("Income");
            user.setBalance(user.getBalance() + income.getValueTransaction());
            user.getTransactionList().add(income);
            user.getIncomesList().add(income);
            userRepository.save(user);
        }
    }
    public List<Transaction> listAllTransactions(String userId) {
        List<Transaction> transactionList = userService.verifyUser(userId).getTransactionList();
        if (transactionList.isEmpty() || transactionList == null)  {
            throw  new NullPointerException("Lista está vazia");
        }
        return transactionList;
    }
    public List<Bill> listAllBills(String userId){
        List<Bill> transactionBills = userService.verifyUser(userId).getBillsList();
        if (transactionBills.isEmpty() || transactionBills == null)  {
            throw  new NullPointerException("Lista está vazia");
        }
        return transactionBills;
    }
    public List<Income> listAllIncomes(String userId){
        List<Income> transactionIncome = userService.verifyUser(userId).getIncomesList();
        if (transactionIncome.isEmpty() || transactionIncome == null)  {
            throw  new NullPointerException("Lista está vazia");
        }
        return transactionIncome;
    }
    public void updateTransactionInfo(String userId, String transactionId, TransactionRequest transactionRequest){
        User user = userService.verifyUser(userId);
        String typeTransaction = null;
        for (Transaction t : user.getTransactionList()) {
            if (t.getIdTransaction().equals(transactionId)) {
                t.setNameTransaction(transactionRequest.getNameTransaction());
                t.setValueTransaction(transactionRequest.getValueTransaction());
                t.setTypeTransaction(transactionRequest.getTypeTransaction());
                typeTransaction = t.getTypeBill_Income();
                break;
            }
        }


        switch (typeTransaction){
           case "Bill" -> {
                for (Bill b: user.getBillsList()){
                    if (Objects.equals(b.getIdTransaction(), transactionId)){
                        b.setNameTransaction(transactionRequest.getNameTransaction());
                        b.setValueTransaction(transactionRequest.getValueTransaction());
                        b.setTypeTransaction(transactionRequest.getTypeTransaction());
                        break;
                    }
                }

           }
           case "Income" -> {
               for (Income i : user.getIncomesList()){
                   i.setNameTransaction(transactionRequest.getNameTransaction());
                   i.setValueTransaction(transactionRequest.getValueTransaction());
                   i.setTypeTransaction(transactionRequest.getTypeTransaction());
                   break;
                   }
               }
           }
        userRepository.save(user);
        }

        }



