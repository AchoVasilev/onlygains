package com.project.infrastructure.exceptions;

import java.util.UUID;

public class DuplicateEntryException extends RuntimeException {

    public DuplicateEntryException(Class<?> className, UUID id) {
        super(String.format("%s already exists. %s=%s", className.getSimpleName(), className.getSimpleName(), id));
    }
}
