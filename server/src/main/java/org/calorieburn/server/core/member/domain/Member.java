package org.calorieburn.server.core.member.domain;

import lombok.Getter;
import org.calorieburn.server.core.member.exception.MemberErrorCode;
import org.calorieburn.server.global.exception.type.ApiException;

@Getter
public class Member {

    private final Long id;
    private final String name;
    private final String email;
    private final String password;
    private final String phone;
    private final String school;

    public Member(Long id, String name, String email, String password, String phone, String school) {
        validateName(name);
        validateEmail(email);
        validatePassword(password);
        validatePhone(phone);
        validateSchool(school);

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.school = school;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new ApiException(MemberErrorCode.M001);
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new ApiException(MemberErrorCode.M002);
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new ApiException(MemberErrorCode.M003);
        }
    }

    private void validatePhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            throw new ApiException(MemberErrorCode.M004);
        }
    }

    private void validateSchool(String school) {
        if (school == null || school.isEmpty()) {
            throw new ApiException(MemberErrorCode.M005);
        }
    }

}
