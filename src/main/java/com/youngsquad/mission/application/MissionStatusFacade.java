package com.youngsquad.mission.application;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.mission.domain.Mission;
import com.youngsquad.mission.domain.MissionStatus;
import com.youngsquad.travel.domain.service.mission.MissionStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionStatusFacade {
    private final MissionStatusRepository missionStatusRepository;
    public void saveMissionStatus(Mission mission){
        MissionStatus missionStatus = MissionStatus.makeEntity(mission);
        missionStatusRepository.save(missionStatus);
    }

    public void stopMission(Mission mission){
        MissionStatus missionStatus = missionStatusRepository.findByMission(mission).orElseThrow(() -> new BusinessException(ErrorCode.MISSION_NOT_FOUND));
        missionStatus.stop();
        missionStatusRepository.save(missionStatus);
    }

    public void endMission(Mission mission){
        MissionStatus missionStatus = missionStatusRepository.findByMission(mission).orElseThrow(() -> new BusinessException(ErrorCode.MISSION_NOT_FOUND));
        missionStatus.end();
        missionStatusRepository.save(missionStatus);
    }
}
