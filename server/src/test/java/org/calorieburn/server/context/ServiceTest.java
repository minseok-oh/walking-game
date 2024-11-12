package org.calorieburn.server.context;

import org.calorieburn.server.core.member.infra.MemberCoreRepository;
import org.calorieburn.server.core.member.service.AuthService;
import org.calorieburn.server.core.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public abstract class ServiceTest {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected MemberCoreRepository memberCoreRepository;

    @Autowired
    protected MemberService memberService;

    @Autowired
    protected AuthService authService;

}
