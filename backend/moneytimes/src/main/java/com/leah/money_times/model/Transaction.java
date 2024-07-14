package com.leah.money_times.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Transaction {
    public String generateIdTransaction(){
         long id = Math.abs(new Random().nextLong());
         return Long.toString(id);
    }
    @Id
    String idTransaction = generateIdTransaction();
    String nameTransaction;
    String typeTransaction;
    double valueTransaction;
    LocalDate dateTransaction = LocalDate.now();
    String  typeBill_Income;
}
