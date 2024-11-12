package org.calorieburn.server.core.health.infra;

import lombok.RequiredArgsConstructor;
import org.calorieburn.server.core.health.domain.HealthInfo;
import org.calorieburn.server.core.health.domain.HealthInfoRepository;
import org.calorieburn.server.core.health.entity.HealthInfoEntity;
import org.calorieburn.server.global.util.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HealthInfoCoreRepository implements HealthInfoRepository {

    private final HealthInfoJpaRepository healthInfoJpaRepository;

    @Override
    public HealthInfo save(HealthInfo healthInfo) {
        HealthInfoEntity healthInfoEntity = Mapper.convertToHealthInfoEntity(healthInfo);
        HealthInfoEntity savedHealthInfoEntity = healthInfoJpaRepository.save(healthInfoEntity);
        return Mapper.convertToHealthInfo(savedHealthInfoEntity);
    }

    @Override
    public HealthInfo findById(Long id) {
        HealthInfoEntity healthInfoEntity = healthInfoJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 건강정보가 존재하지 않습니다."));
        return Mapper.convertToHealthInfo(healthInfoEntity);
    }

    @Override
    public HealthInfo findByMemberId(Long memberId) {
        HealthInfoEntity healthInfoEntity = healthInfoJpaRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 memberId의 건강정보가 존재하지 않습니다."));
        return Mapper.convertToHealthInfo(healthInfoEntity);
    }
}
