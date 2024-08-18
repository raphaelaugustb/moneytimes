package com.leah.money_times.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record TransactionRequest(String nameTransaction, String typeTransaction, double valueTransaction) {
}
