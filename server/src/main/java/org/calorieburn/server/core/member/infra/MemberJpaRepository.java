package org.calorieburn.server.core.member.infra;

import java.util.List;
import java.util.Optional;
import org.calorieburn.server.core.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByEmail(String email);

    List<MemberEntity> findBySchool(String school);

    @Query("SELECT m.school, SUM(h.calorie) AS totalCalories " +
            "FROM HealthInfoEntity h " +
            "JOIN MemberEntity m ON h.memberId = m.id " +
            "GROUP BY m.school")
    List<Object[]> findTotalCaloriesBySchool();
}
