package com.leah.money_times.entity;


import com.leah.money_times.model.Bill;
import com.leah.money_times.model.Income;
import com.leah.money_times.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private List<Bill> billsList;
    private List<Income> incomesList;
    private List<Transaction> transactionList;
    private double balance;
}
