package com.example.insuranceAssist.exception;

public class InvalidPhoneNumberException extends Exception {

    public InvalidPhoneNumberException() {}

    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
