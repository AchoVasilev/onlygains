package com.project.application.models.post;

import java.util.UUID;

public record PostView(UUID id, String title, String text) {
}
