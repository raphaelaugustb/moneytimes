package com.leah.money_times.exception;

public class InvalidTransaction extends RuntimeException {
    public InvalidTransaction(String message) {
        super(message);
    }
}
