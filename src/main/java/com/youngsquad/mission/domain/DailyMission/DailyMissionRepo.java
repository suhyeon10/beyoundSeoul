package com.youngsquad.mission.domain.DailyMission;

import com.youngsquad.mission.domain.PersonMission.PersonMissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyMissionRepo extends JpaRepository<
        DailyMission, Long> {

    List<DailyMission> getDailyMissionsByDestination(String destination);
}
