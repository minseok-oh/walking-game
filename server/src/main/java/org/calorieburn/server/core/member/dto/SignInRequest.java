package org.calorieburn.server.core.member.dto;

public record SignInRequest(
        String email,
        String password
) {
}
