package org.calorieburn.server.core.siege.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import org.calorieburn.server.context.ServiceTest;
import org.calorieburn.server.core.siege.domain.Siege;
import org.calorieburn.server.core.siege.dto.DetailSiegeInfo;
import org.calorieburn.server.core.siege.dto.SimpleSiegeInfo;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class SiegeServiceTest extends ServiceTest {

    @Nested
    class getSiegeList_메서드는 {

        @Test
        void 현재_진행중인_공성전_리스트를_조회한다() {
            // given
            Siege siege1 = new Siege(
                    1L,
                    "test1",
                    "test1",
                    "test1",
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(1)
            );
            siegeCoreRepository.save(siege1);

            Siege siege2 = new Siege(
                    2L,
                    "test2",
                    "test2",
                    "test2",
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(1)
            );
            siegeCoreRepository.save(siege2);

            // when
            List<SimpleSiegeInfo> siegeList = siegeService.getSiegeList();

            // then
            assertEquals(2, siegeList.size());
        }
    }

    @Nested
    class getSiegeDetail_메서드는 {

        @Test
        void 공성전_상세를_조회한다() {
            // given
            Siege siege = new Siege(
                    1L,
                    "test",
                    "test",
                    "test",
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(1)
            );
            Siege savedSiege = siegeCoreRepository.save(siege);

            // when
            DetailSiegeInfo siegeDetail = siegeService.getSiegeDetail(savedSiege.getId());

            // then
            assertEquals(savedSiege.getTitle(), siegeDetail.title());
            assertEquals(savedSiege.getSchool1(), siegeDetail.school1());
            assertEquals(savedSiege.getSchool2(), siegeDetail.school2());
        }
    }

    @Nested
    class createSiege_메서드는 {

        @Test
        void 공성전을_생성한다() {
            // given
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime tomorrow = now.plusDays(1);
            String title = "test";
            String school1 = "test";
            String school2 = "test";

            // when
            siegeService.createSiege(title, school1, school2, now, tomorrow);

            // then
            List<Siege> sieges = siegeCoreRepository.findByEndedAtAfter(now);
            assertEquals(1, sieges.size());
            Siege savedSiege = sieges.get(0);
            assertEquals(title, savedSiege.getTitle());
            assertEquals(school1, savedSiege.getSchool1());
            assertEquals(school2, savedSiege.getSchool2());
        }
    }
}