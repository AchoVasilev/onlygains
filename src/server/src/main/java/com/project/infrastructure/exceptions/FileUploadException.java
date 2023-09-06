package com.project.infrastructure.exceptions;

public class FileUploadException extends RuntimeException {
    public FileUploadException(Class<?> className, Throwable throwable) {
        super(String.format("Could not upload file. %s",
                className.getSimpleName()), throwable);
    }
}
