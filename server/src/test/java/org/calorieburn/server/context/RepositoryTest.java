package org.calorieburn.server.context;

import org.calorieburn.server.core.health.infra.HealthInfoCoreRepository;
import org.calorieburn.server.core.health.infra.HealthInfoJpaRepository;
import org.calorieburn.server.core.member.infra.MemberCoreRepository;
import org.calorieburn.server.core.member.infra.MemberJpaRepository;
import org.calorieburn.server.core.siege.infra.SiegeCoreRepository;
import org.calorieburn.server.core.siege.infra.SiegeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import({MemberCoreRepository.class, HealthInfoCoreRepository.class, SiegeCoreRepository.class})
@DataJpaTest
public abstract class RepositoryTest {

    @Autowired
    public MemberJpaRepository memberJpaRepository;

    @Autowired
    public HealthInfoJpaRepository healthInfoJpaRepository;

    @Autowired
    public SiegeJpaRepository siegeJpaRepository;
}