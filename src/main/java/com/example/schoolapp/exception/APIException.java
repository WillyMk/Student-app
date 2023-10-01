package com.example.schoolapp.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

public class APIException extends RuntimeException{
    private final ErrorObject errorObject;
    public APIException(ErrorObject errorObject){
        super(errorObject.getMessage());
        this.errorObject = errorObject;
    }

//    public static APIException badRequest(final String message, final Object... args) {
//        return new APIException(
//                new ErrorObject(HttpStatus.BAD_REQUEST, MessageFormat.format(message, args))
//        );
//    }

    public static APIException notFound(String message, final Object... args) {
        return new APIException(
                new ErrorObject(HttpStatus.NOT_FOUND, MessageFormat.format(message, args))
        );
    }


//    public static APIException conflict(final String message, final Object... args) {
//        return new APIException(
//                new ErrorObject(HttpStatus.CONFLICT, MessageFormat.format(message, args))
//        );
//    }

//    public static APIException internalError(final String message, final Object... args) {
//        return new APIException(
//                new ErrorObject(HttpStatus.INTERNAL_SERVER_ERROR, MessageFormat.format(message, args))
//        );
//    }

//    public ErrorObject apiError() {
//        return this.errorObject;
//    }

    @Override
    public String toString() {
        return "APIException{"
                + "apiError=" + errorObject
                + '}';
    }

}
