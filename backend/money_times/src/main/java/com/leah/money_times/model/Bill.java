package com.leah.money_times.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
public class Bill extends Transaction {

    public Bill(Long id, String nameTransaction, String typeTransaction, double valueTransaction, LocalDate dateTransaction, String typeBill_Income) {
        super(id, nameTransaction, typeTransaction, valueTransaction, dateTransaction, typeBill_Income);
    }


    public Bill() {
    }
}
