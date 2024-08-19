package com.leah.money_times.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Setter
@Getter
public class ModelHandler {
    private HttpStatus httpStatus;
    private String message;
    private int statusCode;
}
