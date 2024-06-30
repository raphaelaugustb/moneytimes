package com.leah.money_times.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Income extends Transaction{
    public Income(Long id, String nameTransaction, String typeTransaction, double valueTransaction, LocalDate dateTransaction, String typeBill_Income) {
        super(id, nameTransaction, typeTransaction, valueTransaction, dateTransaction, typeBill_Income);
    }

    public Income() {
    }
}
