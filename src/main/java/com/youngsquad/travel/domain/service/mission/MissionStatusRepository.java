package com.youngsquad.travel.domain.service.mission;

import com.youngsquad.mission.domain.Mission;
import com.youngsquad.mission.domain.MissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionStatusRepository extends JpaRepository<MissionStatus, Long> {
    List<MissionStatus> findByMission(Mission mission);
}
