package com.youngsquad.travel.domain.service.mission;

import com.youngsquad.mission.domain.Mission;
import com.youngsquad.travel.domain.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findByTravel(Travel travel);
}
