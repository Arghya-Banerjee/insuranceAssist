package com.example.insuranceAssist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidEmailException extends Exception {

    public InvalidEmailException(){}

    public InvalidEmailException(String message) {
        super(message);
    }

}
