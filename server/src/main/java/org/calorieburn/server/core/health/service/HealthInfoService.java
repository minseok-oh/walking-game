package org.calorieburn.server.core.health.service;

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
}
