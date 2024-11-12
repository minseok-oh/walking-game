package org.calorieburn.server.core.member.dto;

public record SignInRequestDto(
        String email,
        String password
) {
}
