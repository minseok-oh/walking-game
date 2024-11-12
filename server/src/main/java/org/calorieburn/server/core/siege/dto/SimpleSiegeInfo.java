package org.calorieburn.server.core.siege.dto;

import org.calorieburn.server.core.siege.domain.Siege;

public record SimpleSiegeInfo(
        Long id,
        String title,
        String school1,
        String school2
) {
    public static SimpleSiegeInfo from(Siege siege) {
        return new SimpleSiegeInfo(
                siege.getId(),
                siege.getTitle(),
                siege.getSchool1(),
                siege.getSchool2()
        );
    }
}
