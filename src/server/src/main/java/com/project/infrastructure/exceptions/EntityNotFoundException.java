package com.project.infrastructure.exceptions;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class<?> className, UUID id) {
        super(String.format("%s does not exist. %s=%s",
                className.getSimpleName(), className.getSimpleName(), id));
    }
}
