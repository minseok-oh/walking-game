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
}