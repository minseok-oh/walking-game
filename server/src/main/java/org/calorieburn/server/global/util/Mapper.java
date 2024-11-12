package org.calorieburn.server.global.util;

import org.calorieburn.server.core.member.domain.Member;
import org.calorieburn.server.core.member.entity.MemberEntity;

public class Mapper {

    public static MemberEntity convertToMemberEntity(Member member) {
        return new MemberEntity(
            member.getId(),
            member.getName(),
            member.getEmail(),
            member.getPassword(),
            member.getPhone(),
            member.getSchool()
        );
    }

    public static Member convertToMember(MemberEntity memberEntity) {
        return new Member(
            memberEntity.getId(),
            memberEntity.getName(),
            memberEntity.getEmail(),
            memberEntity.getPassword(),
            memberEntity.getPhone(),
            memberEntity.getSchool()
        );
    }
}
