package com.example.schoolapp.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorObject {
    @JsonIgnore
    private HttpStatus statusCode;
    private String message;
    private Date timeStamp;

    public ErrorObject(HttpStatus httpStatus, String format) {
        this.statusCode= httpStatus;
        this.setMessage(httpStatus.getReasonPhrase());
    }

}
