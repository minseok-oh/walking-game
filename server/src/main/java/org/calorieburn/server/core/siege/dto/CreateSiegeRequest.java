package org.calorieburn.server.core.siege.dto;

import java.time.LocalDateTime;

public record CreateSiegeRequest(
        String title,
        String school1,
        String school2,
        LocalDateTime startedAt,
        LocalDateTime endedAt
) {
}
