package com.leah.money_times.services;

import com.leah.money_times.entity.User;
import com.leah.money_times.exception.InvalidTransaction;
import com.leah.money_times.model.Bill;
import com.leah.money_times.model.Income;
import com.leah.money_times.model.Transaction;
import com.leah.money_times.repository.UserRepository;
import com.leah.money_times.request.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionService {

    UserRepository userRepository;
    UserService userService;

    public TransactionService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    //Verificar transação
    private void verifyTransaction(TransactionRequest transactionRequest) {
        if (transactionRequest.typeTransaction() == null || transactionRequest.nameTransaction() == null || transactionRequest.valueTransaction() == 0)
            throw new InvalidTransaction("Invalid transaction request: Missing Required Fields");
    }

    public void createNewBIll(String userId, TransactionRequest transactionRequest) {
        User user = userService.verifyUser(userId);
        Bill bill = new Bill();
        verifyTransaction(transactionRequest);

        bill.setNameTransaction(transactionRequest.nameTransaction());
        bill.setValueTransaction(transactionRequest.valueTransaction());
        bill.setTypeTransaction(transactionRequest.typeTransaction());
        bill.setTypeBill_Income("Bill");
        user.setBalance(user.getBalance() - bill.getValueTransaction());
        user.getTransactionList().add(bill);
        user.getBillsList().add(bill);
        userRepository.save(user);
    }

    public void createNewIncome(String userId, TransactionRequest transactionRequest) {
        User user = userService.verifyUser(userId);
        verifyTransaction(transactionRequest);
        Income income = new Income();
        income.setNameTransaction(transactionRequest.nameTransaction());
        income.setValueTransaction(transactionRequest.valueTransaction());
        income.setTypeTransaction(transactionRequest.typeTransaction());
        income.setTypeBill_Income("Income");
        user.setBalance(user.getBalance() + income.getValueTransaction());
        user.getTransactionList().add(income);
        user.getIncomesList().add(income);
        userRepository.save(user);
    }


    public List<Transaction> listAllTransactions(String userId) {
        List<Transaction> transactionList = userService.verifyUser(userId).getTransactionList();
        if (transactionList.isEmpty()) {
            throw new NullPointerException("Lista está vazia");
        }
        return transactionList;
    }

    public List<Bill> listAllBills(String userId) {
        List<Bill> transactionBills = userService.verifyUser(userId).getBillsList();
        if (transactionBills.isEmpty()) {
            throw new NullPointerException("Lista está vazia");
        }
        return transactionBills;
    }

    public List<Income> listAllIncomes(String userId) {
        List<Income> transactionIncome = userService.verifyUser(userId).getIncomesList();
        if (transactionIncome.isEmpty()) {
            throw new NullPointerException("Lista está vazia");
        }
        return transactionIncome;
    }

    public List<Transaction> listTransactionByValue(String userId, int initValue, int finalValue) {
        User user = userService.verifyUser(userId);
        List<Transaction> transactionListByValue = new ArrayList<>();
        for (Transaction t : user.getTransactionList()) {
            if (t.getValueTransaction() >= initValue && t.getValueTransaction() <= finalValue) {
                transactionListByValue.add(t);
            }
        }
        return transactionListByValue;
    }

    public List<Transaction> listTransactionByName(String userId, String nameTransaction) {
        User user = userService.verifyUser(userId);
        List<Transaction> transactionListByName = new ArrayList<>();
        for (Transaction t : user.getTransactionList()) {
            if (t.getNameTransaction().equals(nameTransaction)) {
                transactionListByName.add(t);
            }
        }
        return transactionListByName;
    }

    public List<Transaction> listTransactionByType(String userId, String typeTransaction) {
        User user = userService.verifyUser(userId);
        List<Transaction> transactionListByType = new ArrayList<>();
        for (Transaction t : user.getTransactionList()) {
            if (t.getTypeTransaction().equals(typeTransaction)) {
                transactionListByType.add(t);
            }
        }
        return transactionListByType;
    }

    public void deleteTransactionById(String userId, String transactionId) {
        User user = userService.verifyUser(userId);
        Transaction transactionRemove = null;
        Income incomeRemove = null;
        Bill billRemove = null;
        for (Transaction transaction : user.getTransactionList()) {
            if (Objects.equals(transaction.getIdTransaction(), transactionId)) {
                transactionRemove = transaction;
                break;
            }
        }
        for (Bill bill : user.getBillsList()) {
            if (Objects.equals(bill.getIdTransaction(), transactionId)) {
                billRemove = bill;
                break;
            }
        }
        for (Income income : user.getIncomesList()) {
            if (Objects.equals(income.getIdTransaction(), transactionId)) {
                incomeRemove = income;
                break;
            }
        }
        if (billRemove == null) {
            user.setBalance(user.getBalance() - incomeRemove.getValueTransaction());
        }
        if (incomeRemove == null) {
            user.setBalance(user.getBalance() + billRemove.getValueTransaction());
        }
        if (incomeRemove != null && billRemove != null) {
            user.setBalance(user.getBalance() - incomeRemove.getValueTransaction() + billRemove.getValueTransaction());
        }

        user.getBillsList().remove(billRemove);
        user.getTransactionList().remove(transactionRemove);
        user.getIncomesList().remove(incomeRemove);
        userRepository.save(user);
    }


    public void updateTransactionInfo(String userId, String transactionId, TransactionRequest transactionRequest) {
        User user = userService.verifyUser(userId);
        String typeTransaction = null;
        double incomeBalance = 0;
        double billsBalance = 0;
        for (Transaction t : user.getTransactionList()) {
            if (t.getIdTransaction().equals(transactionId)) {
                verifyTransaction(transactionRequest);
                t.setNameTransaction(transactionRequest.nameTransaction());
                t.setValueTransaction(transactionRequest.valueTransaction());
                t.setTypeTransaction(transactionRequest.typeTransaction());
                typeTransaction = t.getTypeBill_Income();
                break;
            }
        }


        switch (typeTransaction) {
            case "Bill" -> {
                for (Bill b : user.getBillsList()) {
                    if (Objects.equals(b.getIdTransaction(), transactionId)) {
                        verifyTransaction(transactionRequest);
                        b.setNameTransaction(transactionRequest.nameTransaction());
                        b.setValueTransaction(transactionRequest.valueTransaction());
                        b.setTypeTransaction(transactionRequest.typeTransaction());
                        break;
                    }
                }

            }
            case "Income" -> {
                for (Income i : user.getIncomesList()) {
                    if (Objects.equals(i.getIdTransaction(), transactionId)) {
                        verifyTransaction(transactionRequest);
                        i.setNameTransaction(transactionRequest.nameTransaction());
                        i.setValueTransaction(transactionRequest.valueTransaction());
                        i.setTypeTransaction(transactionRequest.typeTransaction());
                        break;
                    }
                }
            }
             default -> {
                throw new InvalidTransaction("Invalid transaction request: Invalid type transaction");
             }
        }
        for (Income i : user.getIncomesList()) {
            incomeBalance += i.getValueTransaction();
        }
        for (Bill b : user.getBillsList()) {
            billsBalance += b.getValueTransaction();
        }
        user.setBalance(incomeBalance - billsBalance);
        userRepository.save(user);
    }
}


