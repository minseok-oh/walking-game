package org.calorieburn.server.core.member.service;

import lombok.RequiredArgsConstructor;
import org.calorieburn.server.core.member.domain.Member;
import org.calorieburn.server.core.member.dto.MemberInfoResponse;
import org.calorieburn.server.core.member.infra.MemberCoreRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberCoreRepository memberCoreRepository;

    /**
     * 회원 정보 조회를 위한 메서드
     *
     * @param id 회원 id
     * @return 회원 정보 응답
     */
    public MemberInfoResponse getMemberInfo(Long id) {
        Member member = memberCoreRepository.findById(id);
        return MemberInfoResponse.of(member);
    }

    /**
     * 학교별 점수 합산 조회를 위한 메서드
     *
     * @param school 학교 이름
     * @return 학교별 점수 합산
     */
    public Long getSchoolScoreSum(String school) {
        return memberCoreRepository.getSchoolScoreSum(school);
    }
}
