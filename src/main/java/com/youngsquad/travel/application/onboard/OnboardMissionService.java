package com.youngsquad.travel.application.onboard;

import com.youngsquad.mission.domain.Mission;
import com.youngsquad.mission.domain.MissionStatus;
import com.youngsquad.mission.domain.MissionStatusType;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.mission.MissionCategory;
import com.youngsquad.travel.domain.model.mission.TravelMissionSample;
import com.youngsquad.travel.domain.service.mission.MissionRepository;
import com.youngsquad.travel.domain.service.mission.MissionStatusRepository;
import com.youngsquad.travel.domain.service.mission.TravelMissionSampleRepository;
import com.youngsquad.travel.presentation.request.OnboardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OnboardMissionService {
    private final TravelMissionSampleRepository travelMissionSampleRepository;
    private final MissionRepository missionRepository;
    private final MissionStatusRepository missionStatusRepository;

    public void createMission(OnboardRequest onboardRequest, Travel travel) {
        List<Mission> missions = new ArrayList<>();

        addRandomMissionSample(travel, missions, MissionCategory.TOUR, onboardRequest.getDestination());
        addRandomMissionSample(travel, missions, MissionCategory.SOSO, onboardRequest.getDestination());
        addRandomMissionSample(travel, missions, MissionCategory.FOOD, onboardRequest.getDestination());

        for(Mission mission : missions) saveMissionStatus(mission);

    }
    public void saveMissionStatus(Mission mission){
        missionRepository.save(mission);
        MissionStatus missionStatus = MissionStatus.makeEntity(mission);
        missionStatusRepository.save(missionStatus);
    }

    private void addRandomMissionSample(Travel travel, List<Mission> missions, MissionCategory missionCategory, String destination) {
        List<TravelMissionSample> missionSamples = travelMissionSampleRepository.findByTravelMissionDestinationAndMissionCategory(destination, missionCategory);
        if (!missionSamples.isEmpty()) {
            Collections.shuffle(missionSamples);
            missions.add(Mission.makeEntity(travel, getRandomElement(missionSamples)));
        }
    }

    private <T> T getRandomElement(List<T> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }


}