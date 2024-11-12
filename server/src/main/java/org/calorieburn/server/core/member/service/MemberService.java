package org.calorieburn.server.core.member.service;

import lombok.RequiredArgsConstructor;
import org.calorieburn.server.core.member.domain.Member;
import org.calorieburn.server.core.member.infra.MemberCoreRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberCoreRepository memberCoreRepository;
    private final PasswordEncoder passwordEncoder;

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
        return memberCoreRepository.save(member);
    }
}
