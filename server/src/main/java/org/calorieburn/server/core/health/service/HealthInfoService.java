package org.calorieburn.server.core.health.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.calorieburn.server.core.health.domain.HealthInfo;
import org.calorieburn.server.core.health.dto.HealthInfoResponse;
import org.calorieburn.server.core.health.infra.HealthInfoCoreRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthInfoService {

    private final HealthInfoCoreRepository healthInfoCoreRepository;

    /**
     * 건강정보 조회
     * @param memberId 회원 id
     * @return
     */
    public HealthInfoResponse getHealthInfo(Long memberId) {
        HealthInfo healthInfo = healthInfoCoreRepository.findByMemberId(memberId);
        return new HealthInfoResponse(
                healthInfo.getWalkingStep(),
                healthInfo.getCalorie(),
                healthInfo.getBeforeWeight(),
                healthInfo.getAfterWeight()
        );
    }

    /**
     * 기본 건강정보 저장
     * @param memberId 사용자 ID
     */
    public HealthInfo createHealthInfo(Long memberId) {
        HealthInfo healthInfo = new HealthInfo(null, 0L, 0L, 0L, 0L, memberId);
        HealthInfo savedHealthInfo = healthInfoCoreRepository.save(healthInfo);
        return savedHealthInfo;
    }

    /**
     * 운동 정보 추가
     * @param memberId 사용자 ID
     * @param walkingStep 걸음 수
     * @param calorie 소모 칼로리
     * @return
     */
    @Transactional
    public void appendExercise(Long memberId, Long walkingStep, Long calorie) {
        HealthInfo healthInfo = healthInfoCoreRepository.findByMemberId(memberId);
        HealthInfo appendedHealthInfo = healthInfo.appendExercise(walkingStep, calorie);
        healthInfoCoreRepository.save(appendedHealthInfo);
    }

    /**
     * 현재 체중 업데이트
     * @param memberId 사용자 ID
     * @param currentWeight 현재 체중
     */
    @Transactional
    public void updateCurrentWeight(Long memberId, Long currentWeight) {
        HealthInfo healthInfo = healthInfoCoreRepository.findByMemberId(memberId);
        HealthInfo updatedHealthInfo = healthInfo.updateCurrentWeight(currentWeight);
        healthInfoCoreRepository.save(updatedHealthInfo);
    }
}
