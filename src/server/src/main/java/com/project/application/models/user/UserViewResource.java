package com.project.application.models.user;

import com.project.domain.user.User;
import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record UserViewResource(UUID id, String fullName, String email) {
    public static UserViewResource from(User user) {
        return new UserViewResource(user.getId(), user.getFullName(), user.getEmail());
    }
}
