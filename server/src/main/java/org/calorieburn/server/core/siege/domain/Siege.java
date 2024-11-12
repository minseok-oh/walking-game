package org.calorieburn.server.core.siege.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import org.calorieburn.server.core.siege.exception.SiegeErrorCode;
import org.calorieburn.server.global.exception.type.ApiException;

@Getter
public class Siege {

    private final Long id;
    private final String title;
    private final String school1;
    private final String school2;
    private final LocalDateTime startedAt;
    private final LocalDateTime endedAt;

    public Siege(Long id, String title, String school1, String school2, LocalDateTime startedAt, LocalDateTime endedAt) {
        validateTitle(title);
        validateSchool1(school1);
        validateSchool2(school2);
        validateStartedAt(startedAt);
        validateEndedAt(startedAt, endedAt);

        this.id = id;
        this.title = title;
        this.school1 = school1;
        this.school2 = school2;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    private void validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new ApiException(SiegeErrorCode.S001);
        }
    }

    private void validateSchool1(String school1) {
        if (school1 == null || school1.isEmpty()) {
            throw new ApiException(SiegeErrorCode.S002);
        }
    }

    private void validateSchool2(String school2) {
        if (school2 == null || school2.isEmpty()) {
            throw new ApiException(SiegeErrorCode.S003);
        }
    }

    private void validateStartedAt(LocalDateTime startedAt) {
        if (startedAt == null) {
            throw new ApiException(SiegeErrorCode.S004);
        }
    }

    private void validateEndedAt(LocalDateTime startedAt, LocalDateTime endedAt) {
        if (endedAt.isBefore(startedAt)) {
            throw new ApiException(SiegeErrorCode.S005);
        }

        if (endedAt == null) {
            throw new ApiException(SiegeErrorCode.S005);
        }
    }
}
