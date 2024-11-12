package org.calorieburn.server.context;

import org.calorieburn.server.core.health.infra.HealthInfoCoreRepository;
import org.calorieburn.server.core.health.service.HealthInfoService;
import org.calorieburn.server.core.member.infra.MemberCoreRepository;
import org.calorieburn.server.core.member.service.AuthService;
import org.calorieburn.server.core.member.service.MemberService;
import org.calorieburn.server.core.siege.infra.SiegeCoreRepository;
import org.calorieburn.server.core.siege.service.SiegeService;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public abstract class ServiceTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected MemberCoreRepository memberCoreRepository;

    @Autowired
    protected HealthInfoCoreRepository healthInfoCoreRepository;

    @Autowired
    protected SiegeCoreRepository siegeCoreRepository;

    @Autowired
    protected MemberService memberService;

    @Autowired
    protected AuthService authService;

    @Autowired
    protected HealthInfoService healthInfoService;

    @Autowired
    protected SiegeService siegeService;

    @AfterEach
    void tearDown() {
        databaseCleaner.clear();
    }
}
