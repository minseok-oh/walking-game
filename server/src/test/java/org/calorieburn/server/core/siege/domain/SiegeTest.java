package org.calorieburn.server.core.siege.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.calorieburn.server.core.siege.exception.SiegeErrorCode;
import org.calorieburn.server.global.exception.type.ApiException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SiegeTest {

    @Nested
    class Siege_객체는 {

        @Test
        void 유효한_정보로_생성할_수_있다() {
            // given
            String title = "title";
            String school1 = "school1";
            String school2 = "school2";
            LocalDateTime startedAt = LocalDateTime.now();
            LocalDateTime endedAt = startedAt.plusDays(1);

            // when
            Siege siege = new Siege(null, title, school1, school2, startedAt, endedAt);

            // then
            assertEquals(title, siege.getTitle());
            assertEquals(school1, siege.getSchool1());
            assertEquals(school2, siege.getSchool2());
            assertEquals(startedAt, siege.getStartedAt());
            assertEquals(endedAt, siege.getEndedAt());
        }

        @ParameterizedTest
        @MethodSource("provideInvalidSiegeParams")
        void testSiegeCreationInvalidParams(Long id, String title, String school1, String school2, LocalDateTime startedAt, LocalDateTime endedAt, SiegeErrorCode expectedError) {
            // when & then
            ApiException exception = assertThrows(
                    ApiException.class, () -> new Siege(id, title, school1, school2, startedAt, endedAt));
            assertEquals(expectedError, exception.getErrorCode());
        }

        private static Stream<Arguments> provideInvalidSiegeParams() {
            LocalDateTime now = LocalDateTime.now();
            return Stream.of(
                    Arguments.of(3L, "", "School1", "School2", now, now.plusDays(1), SiegeErrorCode.S001),
                    Arguments.of(4L, "Title", "", "School2", now, now.plusDays(1), SiegeErrorCode.S002),
                    Arguments.of(5L, "Title", "School1", "", now, now.plusDays(1), SiegeErrorCode.S003),
                    Arguments.of(6L, "Title", "School1", "School2", null, now.plusDays(1), SiegeErrorCode.S004),
                    Arguments.of(7L, "Title", "School1", "School2", now, now.minusDays(1), SiegeErrorCode.S005)
            );
        }
    }
}