package org.calorieburn.server.core.siege.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.calorieburn.server.core.siege.domain.Siege;
import org.calorieburn.server.core.siege.dto.CreateSiegeRequest;
import org.calorieburn.server.core.siege.dto.DetailSiegeInfo;
import org.calorieburn.server.core.siege.dto.SimpleSiegeInfo;
import org.calorieburn.server.core.siege.exception.SiegeErrorCode;
import org.calorieburn.server.core.siege.infra.SiegeCoreRepository;
import org.calorieburn.server.global.exception.type.ApiException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiegeService {

    private final SiegeCoreRepository siegeCoreRepository;

    /**
     * 현재 진행중인 공성전 리스트 조회
     * @return
     */
    public List<SimpleSiegeInfo> getSiegeList() {
        List<Siege> sieges = siegeCoreRepository.findByEndedAtAfter(LocalDateTime.now());
        return sieges.stream()
                .map(SimpleSiegeInfo::from)
                .toList();
    }

    /**
     * 공성전 상세 조회
     * @param siegeId 공성전 ID
     * @return
     */
    public DetailSiegeInfo getSiegeDetail(Long siegeId) {
        Siege siege = siegeCoreRepository.findById(siegeId);
        return DetailSiegeInfo.from(siege);
    }

    /**
     * 공성전 생성
     * @param title     공성전 제목
     * @param school1   공성전 진영1
     * @param school2   공성전 진영2
     * @param startedAt 공성전 시작 시간
     * @param endedAt   공성전 종료 시간
     */
    public void createSiege(String title, String school1, String school2, LocalDateTime startedAt, LocalDateTime endedAt) {
        Siege siege = new Siege(
                null,
                title,
                school1,
                school2,
                startedAt,
                endedAt
        );
        siegeCoreRepository.save(siege);
    }
}
