package org.calorieburn.server.core.health.infra;

import java.util.Optional;
import org.calorieburn.server.core.health.entity.HealthInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthInfoJpaRepository extends JpaRepository<HealthInfoEntity, Long> {
    Optional<HealthInfoEntity> findByMemberId(Long memberId);
}
