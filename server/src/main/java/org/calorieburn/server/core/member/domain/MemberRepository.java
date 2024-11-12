package org.calorieburn.server.core.member.domain;

public interface MemberRepository {

    Member save(Member member);

    Member findById(Long id);

}
