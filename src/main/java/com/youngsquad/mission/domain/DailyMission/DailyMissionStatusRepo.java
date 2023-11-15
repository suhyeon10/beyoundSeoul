package com.youngsquad.mission.domain.DailyMission;

import com.youngsquad.mission.domain.PersonMission.PersonMissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailyMissionStatusRepo extends JpaRepository<
        DailyMissionStatus, Long> {
    List<DailyMissionStatus> getDailyMissionStatusesByTravelIdAndUserIdAndStatus(long travelId, long userId, String status);
    List<DailyMissionStatus> getDailyMissionStatusesByTravelIdAndUserIdAndMissionDate(long travelId, long userId, LocalDate createAt);

    List<DailyMissionStatus> getDailyMissionStatusesByTravelIdAndUserId(long travelId, long userId);
    DailyMissionStatus getDailyMissionStatusById(long id);

}
