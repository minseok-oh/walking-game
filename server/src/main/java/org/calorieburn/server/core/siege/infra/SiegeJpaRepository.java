package org.calorieburn.server.core.siege.infra;

import java.time.LocalDateTime;
import java.util.List;
import org.calorieburn.server.core.siege.entity.SiegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiegeJpaRepository extends JpaRepository<SiegeEntity, Long> {

    List<SiegeEntity> findByEndedAtAfter(LocalDateTime now);
}
