package com.leah.money_times.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
public class Bill extends Transaction {
    public Bill(Long id, String nameTransaction, String typeTransaction, double valueTransaction, LocalDate dateTransaction) {
        super(id, nameTransaction, typeTransaction, valueTransaction, dateTransaction);
    }

    public Bill() {
    }
}
