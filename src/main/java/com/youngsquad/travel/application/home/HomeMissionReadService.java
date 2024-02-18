package com.youngsquad.travel.application.home;

import com.youngsquad.mission.domain.Mission;
import com.youngsquad.mission.domain.MissionStatus;
import com.youngsquad.mission.domain.MissionStatusType;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.service.mission.MissionRepository;
import com.youngsquad.travel.domain.service.mission.MissionStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeMissionReadService {
    private final MissionRepository missionRepository;
    private final MissionStatusRepository missionStatusRepository;

    public List<Mission> readMission(Travel travel) {
        List<Mission> missions = missionRepository.findByTravel(travel);
        return missions.stream()
                .filter(this::isOngoingMission)
                .collect(Collectors.toList());
    }

    private boolean isOngoingMission(Mission mission) {
        List<MissionStatus> missionStatusList = missionStatusRepository.findByMission(mission);
        return missionStatusList.stream()
                .anyMatch(missionStatus -> missionStatus.getMissionStatusType() == MissionStatusType.ONGOING);
    }

}
