package com.project.common;

public class Messages {
    private Messages() {
    }

    public static class ErrorMessages {
        private ErrorMessages() {
        }

        public static final String DUPLICATE_POST = "Post with title %s already exists. PostId=%s";
    }
}
