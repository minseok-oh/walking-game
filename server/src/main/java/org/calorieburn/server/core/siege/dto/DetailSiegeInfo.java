package org.calorieburn.server.core.siege.dto;

import java.time.LocalDateTime;
import org.calorieburn.server.core.siege.domain.Siege;

public record DetailSiegeInfo(
        String title,
        String school1,
        String school2,
        LocalDateTime startedAt,
        LocalDateTime endedAt
) {

    public static DetailSiegeInfo from(Siege siege) {
        return new DetailSiegeInfo(
                siege.getTitle(),
                siege.getSchool1(),
                siege.getSchool2(),
                siege.getStartedAt(),
                siege.getEndedAt()
        );
    }
}
