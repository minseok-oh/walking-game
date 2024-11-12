package org.calorieburn.server.core.member.infra;

import lombok.RequiredArgsConstructor;
import org.calorieburn.server.core.member.domain.Member;
import org.calorieburn.server.core.member.domain.MemberRepository;
import org.calorieburn.server.core.member.entity.MemberEntity;
import org.calorieburn.server.global.util.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberCoreRepository implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member save(Member member) {
        MemberEntity memberEntity = Mapper.convertToMemberEntity(member);
        MemberEntity savedMemberEntity = memberJpaRepository.save(memberEntity);
        return Mapper.convertToMember(savedMemberEntity);
    }

    @Override
    public Member findById(Long id) {
        MemberEntity memberEntity = memberJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 회원이 존재하지 않습니다."));
        return Mapper.convertToMember(memberEntity);
    }
}
