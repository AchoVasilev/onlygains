package com.project.messaging.messages;

import java.time.Instant;

public abstract class ApplicationEventBase {
    private final Instant timestamp;
    private String initiator;

    protected ApplicationEventBase() {
        this.timestamp = Instant.now();
    }

    public Instant timestamp() {
        return timestamp;
    }

    public String initiator() {
        return initiator;
    }

    public ApplicationEventBase setInitiator(String initiator) {
        this.initiator = initiator;
        return this;
    }
}
