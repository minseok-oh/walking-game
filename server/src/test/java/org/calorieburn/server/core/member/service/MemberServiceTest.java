package org.calorieburn.server.core.member.service;

import static org.junit.jupiter.api.Assertions.*;

import org.calorieburn.server.context.ServiceTest;
import org.calorieburn.server.core.member.domain.Member;
import org.calorieburn.server.core.member.dto.MemberInfoResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MemberServiceTest extends ServiceTest {

    @Nested
    class getMemberInfo_메서드는 {

        @Test
        void 올바른_아이디를_입력하면_회원정보를_반환한다() {
            // given
            String name = "name";
            String email = "email";
            String password = "password";
            String phone = "phone";
            String school = "school";
            Member savedMember = memberCoreRepository.save(new Member(null, name, email, password, phone, school));

            // when
            MemberInfoResponse memberInfo = memberService.getMemberInfo(savedMember.getId());

            // then
            assertEquals(name, memberInfo.name());
            assertEquals(email, memberInfo.email());
            assertEquals(phone, memberInfo.phone());
            assertEquals(school, memberInfo.school());
        }
    }
}