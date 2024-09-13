package org.example.tasklist.domain.exception;

public class DomainException extends RuntimeException{
    DomainException(String message){
        super(message);
    }
}
