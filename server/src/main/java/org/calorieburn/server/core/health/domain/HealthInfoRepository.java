package org.calorieburn.server.core.health.domain;

public interface HealthInfoRepository {

    HealthInfo save(HealthInfo healthInfo);

    HealthInfo findById(Long id);

    HealthInfo findByMemberId(Long memberId);
}
