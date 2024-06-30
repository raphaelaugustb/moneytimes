package com.leah.money_times.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private String nameTransaction;
    private String typeTransaction;
    private double valueTransaction;
}
