package com.youngsquad.travel.domain.service.mission;

import com.youngsquad.mission.domain.MissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionStatusRepository extends JpaRepository<MissionStatus, Long> {
}
