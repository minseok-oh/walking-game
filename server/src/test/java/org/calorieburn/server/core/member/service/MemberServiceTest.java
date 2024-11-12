package org.calorieburn.server.core.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.calorieburn.server.context.ServiceTest;
import org.calorieburn.server.core.member.domain.Member;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MemberServiceTest extends ServiceTest {

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
            Member savedMember = memberService.signUp(name, email, password, phone, school);

            // then
            Member member = memberCoreRepository.findById(savedMember.getId());
            assertEquals(name, member.getName());
            assertEquals(email, member.getEmail());
            assertEquals(password, member.getPassword());
            assertEquals(phone, member.getPhone());
            assertEquals(school, member.getSchool());
        }
    }
}