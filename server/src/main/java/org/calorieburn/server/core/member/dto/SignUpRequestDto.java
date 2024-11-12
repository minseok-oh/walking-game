package org.calorieburn.server.core.member.dto;

public record SignUpRequestDto(
        String name,
        String email,
        String password,
        String phone,
        String school
) {
}
