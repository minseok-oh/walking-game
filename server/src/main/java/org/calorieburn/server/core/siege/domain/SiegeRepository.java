package org.calorieburn.server.core.siege.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface SiegeRepository {

    List<Siege> findByEndedAtAfter(LocalDateTime now);

    Siege findById(Long id);

    Siege save(Siege siege);
}
