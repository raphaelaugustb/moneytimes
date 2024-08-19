package com.leah.money_times.handler;

import com.leah.money_times.exception.InvalidTransactionException;
import com.leah.money_times.model.ModelHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandleTransactionExceptions extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<ModelHandler> handleInvalidTransaction(InvalidTransactionException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ModelHandler(HttpStatus.UNAUTHORIZED, e.getMessage(), HttpStatus.UNAUTHORIZED.value()));
    }
}
