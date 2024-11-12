package org.calorieburn.server.core.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.calorieburn.server.context.ServiceTest;
import org.calorieburn.server.core.member.domain.Member;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AuthServiceTest extends ServiceTest {

    @Nested
    class signUp_메서드는 {

        @Test
        void 올바른_회원_정보를_입력하면_회원가입에_성공한다() {
            // given
            String name = "name";
            String email = "email";
            String password = "password";
            String phone = "phone";
            String school = "school";

            // when
            Member savedMember = authService.signUp(name, email, password, phone, school);

            // then
            Member member = memberCoreRepository.findById(savedMember.getId());
            assertEquals(name, member.getName());
            assertEquals(email, member.getEmail());
            assertEquals(phone, member.getPhone());
            assertEquals(school, member.getSchool());
        }
    }

    @Nested
    class signIn_메서드는 {

        @Test
        void 올바른_이메일과_비밀번호를_입력하면_로그인에_성공한다() {
            // given
            String email = "email";
            String password = "password";

            Member member = new Member(null, "name", email, passwordEncoder.encode(password), "phone", "school");
            memberCoreRepository.save(member);

            // when
            String accessToken = authService.signIn(email, password);

            // then
            assertNotNull(accessToken);
        }
    }
}