package org.calorieburn.server.global.util;

import org.calorieburn.server.core.health.domain.HealthInfo;
import org.calorieburn.server.core.health.entity.HealthInfoEntity;
import org.calorieburn.server.core.member.domain.Member;
import org.calorieburn.server.core.member.entity.MemberEntity;
import org.calorieburn.server.core.siege.domain.Siege;
import org.calorieburn.server.core.siege.entity.SiegeEntity;

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

    public static HealthInfoEntity convertToHealthInfoEntity(HealthInfo healthInfo) {
        return new HealthInfoEntity(
                healthInfo.getId(),
                healthInfo.getWalkingStep(),
                healthInfo.getCalorie(),
                healthInfo.getBeforeWeight(),
                healthInfo.getAfterWeight(),
                healthInfo.getMemberId()
        );
    }

    public static HealthInfo convertToHealthInfo(HealthInfoEntity healthInfoEntity) {
        return new HealthInfo(
                healthInfoEntity.getId(),
                healthInfoEntity.getWalkingStep(),
                healthInfoEntity.getCalorie(),
                healthInfoEntity.getBeforeWeight(),
                healthInfoEntity.getAfterWeight(),
                healthInfoEntity.getMemberId()
        );
    }

    public static SiegeEntity convertToSiegeEntity(Siege siege) {
        return new SiegeEntity(
                siege.getId(),
                siege.getTitle(),
                siege.getSchool1(),
                siege.getSchool2(),
                siege.getStartedAt(),
                siege.getEndedAt()
        );
    }

    public static Siege convertToSiege(SiegeEntity siegeEntity) {
        return new Siege(
                siegeEntity.getId(),
                siegeEntity.getTitle(),
                siegeEntity.getSchool1(),
                siegeEntity.getSchool2(),
                siegeEntity.getStartedAt(),
                siegeEntity.getEndedAt()
        );
    }
}
