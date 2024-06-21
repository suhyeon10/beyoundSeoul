package com.youngsquad.mission.application;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.mission.domain.Mission;
import com.youngsquad.mission.domain.MissionStatus;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.mission.MissionCategory;
import com.youngsquad.travel.domain.model.mission.TravelMissionSample;
import com.youngsquad.travel.domain.service.mission.MissionRepository;
import com.youngsquad.travel.domain.service.mission.MissionStatusRepository;
import com.youngsquad.travel.domain.service.mission.TravelMissionSampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MissionRefreshService {
    private final MissionRepository missionRepository;
    private final MissionStatusFacade missionStatusFacade;
    private final TravelMissionSampleRepository travelMissionSampleRepository;

    @Transactional
    public void missionRefresh(long missionId){
        //미션 상태 찾고 스탑으로 변경
        // 새로운 미션 할당
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new BusinessException(ErrorCode.MISSION_NOT_FOUND));
        missionStatusFacade.stopMission(mission);

        Mission newMission = getRandomMission(mission.getTravel(),
                mission.getTravelMissionSample().getMissionCategory(),
                mission.getTravel().getDestination()).orElseThrow(()-> new BusinessException(ErrorCode.NOT_FOUND));

        missionRepository.save(mission);
        missionStatusFacade.saveMissionStatus(newMission);
    }

    public Optional<Mission> getRandomMission(Travel travel, MissionCategory missionCategory, String destination) {
        List<TravelMissionSample> missionSamples = travelMissionSampleRepository.findByTravelMissionDestinationAndMissionCategory(destination, missionCategory);
        if (!missionSamples.isEmpty()) {
            Collections.shuffle(missionSamples);
            return Optional.of(Mission.makeEntity(travel, getRandomElement(missionSamples)));
        }
        return Optional.empty(); // No mission available for given criteria
    }

    private <T> T getRandomElement(List<T> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}
