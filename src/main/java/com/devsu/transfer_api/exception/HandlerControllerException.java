package com.devsu.transfer_api.exception;

import com.devsu.transfer_api.dto.exception.ErrorDto;
import com.devsu.transfer_api.enums.ErrorState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;


@RestControllerAdvice
public class HandlerControllerException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handlerException(Exception e) {
        ErrorDto error = new ErrorDto();
        error.setMessage(e.getMessage());
        error.setDate(new Date());
        error.setError(ErrorState.INTERNAL_ERROR.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> runtimeException(Exception e) {
        ErrorDto error = new ErrorDto();
        error.setMessage(e.getMessage());
        error.setDate(new Date());
        error.setError(ErrorState.INTERNAL_ERROR.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        
        return ResponseEntity.internalServerError().body(error);
    }
}
