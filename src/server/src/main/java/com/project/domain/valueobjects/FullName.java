package com.project.domain.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class FullName {
    private String firstName;
    private String lastName;

    private FullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public static FullName of(String firstName, String lastName) {
        return new FullName(firstName,lastName);
    }
}
