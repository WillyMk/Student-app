package com.example.schoolapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalErrorException {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorObject> handleException(APIException ex, WebRequest request){
        ErrorObject error = new ErrorObject();
        error.setMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.NOT_FOUND);
        error.setTimeStamp(new Date());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
