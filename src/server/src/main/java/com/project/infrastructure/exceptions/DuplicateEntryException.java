package com.project.infrastructure.exceptions;

public class DuplicateEntryException extends RuntimeException{
    public DuplicateEntryException(String message) {
        super(message);
    }
}
