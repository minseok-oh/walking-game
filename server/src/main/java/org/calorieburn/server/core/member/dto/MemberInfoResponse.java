package org.calorieburn.server.core.member.dto;

import org.calorieburn.server.core.member.domain.Member;

public record MemberInfoResponse(
        String name,
        String email,
        String phone,
        String school
) {

    public static MemberInfoResponse of(Member member) {
        return new MemberInfoResponse(
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                member.getSchool()
        );
    }
}
