package org.calorieburn.server.core.health.service;

import static org.junit.jupiter.api.Assertions.*;

import org.calorieburn.server.context.ServiceTest;
import org.calorieburn.server.core.health.domain.HealthInfo;
import org.calorieburn.server.core.health.dto.HealthInfoResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HealthInfoServiceTest extends ServiceTest {

    @Nested
    class getHealthInfo_메서드는 {

        @Test
        void 회원_ID로_건강정보를_조회한다() {
            // given
            Long walkingStep = 0L;
            Long calorie = 0L;
            Long beforeWeight = 0L;
            Long afterWeight = 0L;
            Long memberId = 1L;
            healthInfoCoreRepository.save(
                    new HealthInfo(null, walkingStep, calorie, beforeWeight, afterWeight, memberId));

            // when
            HealthInfoResponse healthInfoResponse = healthInfoService.getHealthInfo(memberId);

            // then
            assertEquals(walkingStep, healthInfoResponse.walkingStep());
            assertEquals(calorie, healthInfoResponse.calorie());
            assertEquals(beforeWeight, healthInfoResponse.beforeWeight());
            assertEquals(afterWeight, healthInfoResponse.afterWeight());
        }
    }

    @Nested
    class createHealthInfo_메서드는 {

        @Test
        void 회원_ID로_기본_건강정보를_생성한다() {
            // given
            Long memberId = 1L;

            // when
            HealthInfo healthInfo = healthInfoService.createHealthInfo(memberId);

            // then
            assertNotNull(healthInfo);
            assertEquals(0L, healthInfo.getWalkingStep());
            assertEquals(0L, healthInfo.getCalorie());
            assertEquals(0L, healthInfo.getBeforeWeight());
            assertEquals(0L, healthInfo.getAfterWeight());
            assertEquals(memberId, healthInfo.getMemberId());
        }
    }

    @Nested
    class appendExercise_메서드는 {

        @Test
        void 걸음수와_소모칼로리를_추가한다() {
            // given
            Long walkingStep = 10000L;
            Long calorie = 500L;
            Long beforeWeight = 70000L;
            Long afterWeight = 69000L;
            Long memberId = 1L;
            healthInfoCoreRepository.save(
                    new HealthInfo(null, 0L, 0L, beforeWeight, afterWeight, memberId));

            // when
            healthInfoService.appendExercise(memberId, walkingStep, calorie);
            HealthInfo healthInfo = healthInfoCoreRepository.findByMemberId(memberId);

            // then
            assertEquals(walkingStep, healthInfo.getWalkingStep());
            assertEquals(calorie, healthInfo.getCalorie());
            assertEquals(beforeWeight, healthInfo.getBeforeWeight());
            assertEquals(afterWeight, healthInfo.getAfterWeight());
        }
    }

    @Nested
    class updateCurrentWeight_메서드는 {

        @Test
        void 현재_체중을_업데이트한다() {
            // given
            Long walkingStep = 10000L;
            Long calorie = 500L;
            Long beforeWeight = 70000L;
            Long afterWeight = 69000L;
            Long memberId = 1L;
            healthInfoCoreRepository.save(
                    new HealthInfo(null, walkingStep, calorie, beforeWeight, afterWeight, memberId));
            Long currentWeight = 68000L;

            // when
            healthInfoService.updateCurrentWeight(memberId, currentWeight);
            HealthInfo healthInfo = healthInfoCoreRepository.findByMemberId(memberId);

            // then
            assertEquals(walkingStep, healthInfo.getWalkingStep());
            assertEquals(calorie, healthInfo.getCalorie());
            assertEquals(beforeWeight, healthInfo.getBeforeWeight());
            assertEquals(currentWeight, healthInfo.getAfterWeight());
        }
    }
}