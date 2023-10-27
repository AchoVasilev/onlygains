package com.project.application.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerProvider {
    private LoggerProvider() {
    }

    public static Logger getLogger(Class<?> className) {
        return LoggerFactory.getLogger(className);
    }
}
