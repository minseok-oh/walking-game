package org.calorieburn.server.core.member.domain;


public interface MemberRepository {

    Member save(Member member);

    Member findById(Long id);

    Member findByEmail(String email);

    Long getSchoolScoreSum(String school);
}
