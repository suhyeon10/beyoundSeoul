package com.youngsquad.travel.domain.service.mission;

import com.youngsquad.travel.domain.model.mission.MissionCategory;
import com.youngsquad.travel.domain.model.mission.TravelMissionSample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelMissionSampleRepository extends JpaRepository<TravelMissionSample, Long> {
    List<TravelMissionSample> findByTravelMissionDestinationAndMissionCategory(String destination, MissionCategory missionCategory);
}
