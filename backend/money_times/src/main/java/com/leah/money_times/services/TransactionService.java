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
    private Boolean verifyTransaction(TransactionRequest transactionRequest) {
        if (transactionRequest != null){
            return true;
        } else {
            throw  new NullPointerException("Transação inválida");
        }
    }
    //Verificar fields da transaction
    private TransactionRequest verificateFieldTransactionRequest(Transaction transaction , TransactionRequest transactionRequest ){
        TransactionRequest transactionRequestUpdate =  new TransactionRequest();
        if (transactionRequest.getNameTransaction() != null){
            transactionRequestUpdate.setNameTransaction(transactionRequest.getNameTransaction());
        } else {
            transactionRequestUpdate.setNameTransaction(transaction.getNameTransaction());
        }
        if (transactionRequest.getTypeTransaction() != null){
            transactionRequestUpdate.setTypeTransaction(transactionRequest.getTypeTransaction());
        } else {
            transactionRequestUpdate.setTypeTransaction(transaction.getTypeTransaction());
        }
        if (transactionRequest.getValueTransaction() != 0){
            transactionRequestUpdate.setValueTransaction(transactionRequest.getValueTransaction());
        } else {
            transactionRequestUpdate.setValueTransaction(transaction.getValueTransaction());
        }
        return  transactionRequestUpdate;
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
   public void deleteTransactionById(String userId, String transactionId){
        User user = userService.verifyUser(userId);
        Transaction       transactionRemove = null;
        Income incomeRemove = null;
        Bill billRemove = null;
        for (Transaction transaction : user.getTransactionList()) {
             if (Objects.equals(transaction.getIdTransaction(), transactionId)) {
                 transactionRemove = transaction;
                 break;
        }
        }
        for (Bill bill : user.getBillsList()) {
            if (Objects.equals(bill.getIdTransaction(), transactionId)){
                billRemove = bill;
                break;
            }
        }
       for(Income income : user.getIncomesList()){
           if (Objects.equals(income.getIdTransaction(), transactionId)) {
               incomeRemove = income;
               break;
           }
       }
       user.getBillsList().remove(billRemove);
       user.getTransactionList().remove(transactionRemove);
       user.getIncomesList().remove(incomeRemove);
       userRepository.save(user);
   }


    public void updateTransactionInfo(String userId, String transactionId, TransactionRequest transactionRequest){
        User user = userService.verifyUser(userId);
        String typeTransaction = null;
         double incomeBalance= 0;
        double billsBalance = 0;
        for (Transaction t : user.getTransactionList()) {
            if (t.getIdTransaction().equals(transactionId)) {
                TransactionRequest transactionVerifcated = verificateFieldTransactionRequest(t, transactionRequest);
                t.setNameTransaction(transactionVerifcated.getNameTransaction());
                t.setValueTransaction(transactionVerifcated.getValueTransaction());
                t.setTypeTransaction(transactionVerifcated.getTypeTransaction());
                typeTransaction = t.getTypeBill_Income();
                break;
            }
        }


        switch (typeTransaction){
           case "Bill" -> {
                for (Bill b: user.getBillsList()){
                    if (Objects.equals(b.getIdTransaction(), transactionId)){
                        TransactionRequest transactionVerifcated = verificateFieldTransactionRequest(b, transactionRequest);
                        b.setNameTransaction(transactionVerifcated.getNameTransaction());
                        b.setValueTransaction(transactionVerifcated.getValueTransaction());
                        b.setTypeTransaction(transactionVerifcated.getTypeTransaction());
                        break;
                    }
                }

           }
           case "Income" -> {
               for (Income i : user.getIncomesList()){
                   if (Objects.equals(i.getIdTransaction(), transactionId)) {
                       TransactionRequest transactionVerifcated = verificateFieldTransactionRequest(i, transactionRequest);
                       i.setNameTransaction(transactionVerifcated.getNameTransaction());
                       i.setValueTransaction(transactionVerifcated.getValueTransaction());
                       i.setTypeTransaction(transactionVerifcated.getTypeTransaction());
                       break;
                   }
                   }
               }
           }
        for (Income i : user.getIncomesList()){
            incomeBalance += i.getValueTransaction();
        }
        for (Bill b : user.getBillsList() ){
            billsBalance += b.getValueTransaction();
        }
        user.setBalance(incomeBalance - billsBalance);
        userRepository.save(user);
        }

        }



