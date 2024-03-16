package com.youngsquad.mission.application;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.mission.domain.Mission;
import com.youngsquad.mission.dto.MissionDetailResponse;
import com.youngsquad.travel.domain.model.mission.TravelMissionSample;
import com.youngsquad.travel.domain.service.mission.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionDetailService {

    private final MissionRepository missionRepository;

    public MissionDetailResponse readDetail(long missionId){

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MISSION_NOT_FOUND));

        TravelMissionSample missionSample = mission.getTravelMissionSample();

        if (missionSample == null) {
            throw new BusinessException(ErrorCode.TRAVEL_MISSION_SAMPLE_NOT_FOUND);
        }

        // Set lon and lat to 0 if they are null
        double lon = missionSample.getLongitude() != null ? missionSample.getLongitude() : 0;
        double lat = missionSample.getLatitude() != null ? missionSample.getLatitude() : 0;

        return MissionDetailResponse.builder()
                .title(missionSample.getTitle())
                .detail(missionSample.getDetail())
                .location(missionSample.getAddress())
                .lon(lon)
                .lat(lat)
                .build();
    }
}
