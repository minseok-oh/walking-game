package org.calorieburn.server.core.health.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.calorieburn.server.core.health.exception.HealthInfoErrorCode;
import org.calorieburn.server.global.exception.type.ApiException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HealthInfoTest {

    @Nested
    class HealthInfo_객체는 {

        @Test
        void 유효한_정보로_생성할_수_있다() {
            // given
            Long walkingStep = 10000L;
            Long calorie = 500L;
            Long beforeWeight = 70000L;
            Long afterWeight = 69000L;

            // when
            HealthInfo healthInfo = new HealthInfo(null, walkingStep, calorie, beforeWeight, afterWeight, 1L);

            // then
            assertEquals(walkingStep, healthInfo.getWalkingStep());
            assertEquals(calorie, healthInfo.getCalorie());
            assertEquals(beforeWeight, healthInfo.getBeforeWeight());
            assertEquals(afterWeight, healthInfo.getAfterWeight());
        }

        @ParameterizedTest
        @MethodSource("provideInvalidHealthInfoParams")
        void 잘못된_정보로_생성하면_예외가_발생한다(Long walkingStep, Long calorie, Long beforeWeight, Long afterWeight,
                                   HealthInfoErrorCode expectedError) {
            // when & then
            ApiException exception = assertThrows(
                    ApiException.class,
                    () -> new HealthInfo(1L, walkingStep, calorie, beforeWeight, afterWeight, 1L)
            );
            assertEquals(expectedError, exception.getErrorCode());
        }

        private static Stream<Arguments> provideInvalidHealthInfoParams() {
            return Stream.of(
                    Arguments.of(null, 500L, 70000L, 69000L, HealthInfoErrorCode.H001),
                    Arguments.of(-1L, 500L, 70000L, 69000L, HealthInfoErrorCode.H001),
                    Arguments.of(10000L, null, 70000L, 69000L, HealthInfoErrorCode.H002),
                    Arguments.of(10000L, -1L, 70000L, 69000L, HealthInfoErrorCode.H002),
                    Arguments.of(10000L, 500L, null, 69000L, HealthInfoErrorCode.H003),
                    Arguments.of(10000L, 500L, -1L, 69000L, HealthInfoErrorCode.H003),
                    Arguments.of(10000L, 500L, 70000L, null, HealthInfoErrorCode.H004),
                    Arguments.of(10000L, 500L, 70000L, -1L, HealthInfoErrorCode.H004)
            );
        }
    }

    @Nested
    class appendExercise_메서드는 {

        @Test
        void 걸음수와_소모칼로리를_추가할_수_있다() {
            // given
            HealthInfo healthInfo = new HealthInfo(null, 10000L, 500L, 70000L, 69000L, 1L);
            Long walkingStep = 1000L;
            Long calorie = 50L;

            // when
            HealthInfo appendedHealthInfo = healthInfo.appendExercise(walkingStep, calorie);

            // then
            assertEquals(11000L, appendedHealthInfo.getWalkingStep());
            assertEquals(550L, appendedHealthInfo.getCalorie());
            assertEquals(70000L, appendedHealthInfo.getBeforeWeight());
            assertEquals(69000L, appendedHealthInfo.getAfterWeight());
        }
    }

    @Nested
    class updateCurrentWeight_메서드는 {

        @Test
        void 현재_체중을_업데이트할_수_있다() {
            // given
            HealthInfo healthInfo = new HealthInfo(null, 10000L, 500L, 70000L, 69000L, 1L);
            Long currentWeight = 68000L;

            // when
            HealthInfo updatedHealthInfo = healthInfo.updateCurrentWeight(currentWeight);

            // then
            assertEquals(10000L, updatedHealthInfo.getWalkingStep());
            assertEquals(500L, updatedHealthInfo.getCalorie());
            assertEquals(70000L, updatedHealthInfo.getBeforeWeight());
            assertEquals(68000L, updatedHealthInfo.getAfterWeight());
        }
    }
}