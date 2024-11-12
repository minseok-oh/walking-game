package org.calorieburn.server.core.siege.dto;

import java.time.LocalDateTime;
import org.calorieburn.server.core.siege.domain.Siege;

public record DetailSiegeInfo(
        String title,
        String school1,
        String school2,
        Long school1Score,
        Long school2Score,
        LocalDateTime startedAt,
        LocalDateTime endedAt
) {

}
