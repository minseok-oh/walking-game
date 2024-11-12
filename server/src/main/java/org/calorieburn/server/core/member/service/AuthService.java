package org.calorieburn.server.core.member.service;

import lombok.RequiredArgsConstructor;
import org.calorieburn.server.core.health.service.HealthInfoService;
import org.calorieburn.server.core.member.domain.Member;
import org.calorieburn.server.core.member.exception.MemberErrorCode;
import org.calorieburn.server.core.member.infra.MemberCoreRepository;
import org.calorieburn.server.global.exception.type.ApiException;
import org.calorieburn.server.global.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberCoreRepository memberCoreRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    private final HealthInfoService healthInfoService;

    /**
     * 회원가입을 위한 메서드
     *
     * @param name     이름
     * @param email    이메일
     * @param password 비밀번호
     * @param phone    전화번호
     * @param school   학교
     * @return
     */
    public Member signUp(String name, String email, String password, String phone, String school) {
        String encodedPassword = passwordEncoder.encode(password);

        Member member = new Member(null, name, email, encodedPassword, phone, school);
        Member savedMember = memberCoreRepository.save(member);

        healthInfoService.createHealthInfo(savedMember.getId());
        return savedMember;
    }

    /**
     * 로그인을 위한 메서드
     *
     * @param email    이메일
     * @param password 비밀번호
     */
    public String signIn(String email, String password) {
        Member member = memberCoreRepository.findByEmail(email);

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new ApiException(MemberErrorCode.M006);
        }

        return jwtProvider.createAccessToken(member);
    }
}
