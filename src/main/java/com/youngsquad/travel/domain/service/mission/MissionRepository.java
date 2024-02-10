package com.youngsquad.travel.domain.service.mission;

import com.youngsquad.mission.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
