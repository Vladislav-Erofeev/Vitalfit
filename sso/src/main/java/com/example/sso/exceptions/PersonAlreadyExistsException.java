package com.example.sso.exceptions;

public class PersonAlreadyExistsException extends Exception{
    public PersonAlreadyExistsException(String email) {
        super(String.format("Person with email = %s already exists", email));
    }
}
