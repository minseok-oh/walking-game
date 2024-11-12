package org.calorieburn.server.core.member.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.calorieburn.server.core.member.exception.MemberErrorCode;
import org.calorieburn.server.global.exception.type.ApiException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MemberTest {

    @Nested
    class Member_객체는 {

        @Test
        void 유효한_정보로_생성할_수_있다() {
            // given
            String name = "name";
            String email = "email";
            String password = "password";
            String phone = "phone";
            String school = "school";

            // when
            Member member = new Member(null, name, email, password, phone, school);

            // then
            assertEquals(name, member.getName());
            assertEquals(email, member.getEmail());
            assertEquals(password, member.getPassword());
            assertEquals(phone, member.getPhone());
            assertEquals(school, member.getSchool());
        }

        @ParameterizedTest
        @MethodSource("provideInvalidMemberParams")
        void testMemberCreationInvalidParams(Long id, String name, String email, String password, String phone, String school, MemberErrorCode expectedError) {
            // when & then
            ApiException exception = assertThrows(
                    ApiException.class, () -> new Member(id, name, email, password, phone, school));
            assertEquals(expectedError, exception.getErrorCode());
        }

        private static Stream<Arguments> provideInvalidMemberParams() {
            return Stream.of(
                    Arguments.of(3L, "", "valid.email@example.com", "password123", "01012345678", "University", MemberErrorCode.M001),
                    Arguments.of(4L, "Valid Name", "", "password123", "01012345678", "University", MemberErrorCode.M002),
                    Arguments.of(5L, "Valid Name", "valid.email@example.com", "", "01012345678", "University", MemberErrorCode.M003),
                    Arguments.of(6L, "Valid Name", "valid.email@example.com", "password123", "", "University", MemberErrorCode.M004),
                    Arguments.of(7L, "Valid Name", "valid.email@example.com", "password123", "01012345678", "", MemberErrorCode.M005)
            );
        }
    }
}