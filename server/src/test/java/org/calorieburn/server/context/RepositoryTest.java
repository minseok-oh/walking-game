package org.calorieburn.server.context;

import org.calorieburn.server.core.member.infra.MemberCoreRepository;
import org.calorieburn.server.core.member.infra.MemberJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import({MemberCoreRepository.class})
@DataJpaTest
public abstract class RepositoryTest {

    @Autowired
    public MemberJpaRepository memberJpaRepository;
}