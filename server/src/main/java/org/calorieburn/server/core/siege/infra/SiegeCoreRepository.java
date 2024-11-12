package org.calorieburn.server.core.siege.infra;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.calorieburn.server.core.siege.domain.Siege;
import org.calorieburn.server.core.siege.domain.SiegeRepository;
import org.calorieburn.server.core.siege.entity.SiegeEntity;
import org.calorieburn.server.global.util.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SiegeCoreRepository implements SiegeRepository {

    private final SiegeJpaRepository siegeJpaRepository;

    @Override
    public List<Siege> findByEndedAtAfter(LocalDateTime now) {
        List<SiegeEntity> siegeEntities = siegeJpaRepository.findByEndedAtAfter(now);
        List<Siege> sieges = siegeEntities.stream()
                .map(Mapper::convertToSiege)
                .collect(Collectors.toList());
        return sieges;
    }

    @Override
    public Siege findById(Long id) {
        SiegeEntity siegeEntity = siegeJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 Siege가 존재하지 않습니다."));
        return Mapper.convertToSiege(siegeEntity);
    }

    @Override
    public Siege save(Siege siege) {
        SiegeEntity siegeEntity = Mapper.convertToSiegeEntity(siege);
        SiegeEntity savedSiegeEntity = siegeJpaRepository.save(siegeEntity);
        return Mapper.convertToSiege(savedSiegeEntity);
    }
}
