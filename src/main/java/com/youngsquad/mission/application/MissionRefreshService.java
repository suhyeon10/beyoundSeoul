package com.youngsquad.mission.application;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.mission.domain.Mission;
import com.youngsquad.mission.domain.MissionStatus;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.mission.MissionCategory;
import com.youngsquad.travel.domain.model.mission.TravelMissionSample;
import com.youngsquad.travel.domain.service.TravelRepository;
import com.youngsquad.travel.domain.service.mission.MissionRepository;
import com.youngsquad.travel.domain.service.mission.MissionStatusRepository;
import com.youngsquad.travel.domain.service.mission.TravelMissionSampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    private final TravelRepository travelRepository;

//    @Transactional
//    public void missionRefresh(long missionId){
//        //미션 상태 찾고 스탑으로 변경
//
//        Mission mission = missionRepository.findById(missionId).orElse(null);
//        if(mission !=null){
//            missionStatusFacade.stopMission(mission);
//            missionRepository.save(mission);
//        }
//
//        // 새로운 미션 할당
//        Mission newMission = getRandomMission(mission.getTravel(),
//                mission.getTravelMissionSample().getMissionCategory(),
//                mission.getTravel().getDestination()).orElseThrow(()-> new BusinessException(ErrorCode.NOT_FOUND));
//
//        missionStatusFacade.saveMissionStatus(newMission);
//    }
//





    /**
     * 미션을 새로고침합니다.
     * @param missionId 새로고침할 미션의 ID (null일 수 있음)
     */
    @Transactional
    public void refreshMission(Long missionId, Long travelId) {

        Travel travel = travelRepository.findById(travelId).orElseThrow(() -> new BusinessException(ErrorCode.TRAVEL_NOT_FOUND));
        if (missionId == null) {
            assignRandomMission();
            return;
        }

        Mission mission = missionRepository.findById(missionId).orElse(null);
        if (mission == null) {
            assignRandomMission();
            return;
        }

        MissionCategory category = mission.getTravelMissionSample().getMissionCategory();
        handleMissionRefresh(mission, category);
    }

    /**
     * 주어진 미션과 카테고리에 따라 미션을 새로고침합니다.
     * @param mission 새로고침할 미션
     * @param category 미션의 카테고리
     */
    private void handleMissionRefresh(Mission mission, MissionCategory category) {
        stopCurrentMission(mission);

        if (isDailyMission(category) && isMissionCompletedToday(mission)) {
            // 오늘 이미 완료된 일일 미션이 있으면 새로운 미션을 할당하지 않음
            return;
        }

        assignNewMission(mission.getTravel(), category, mission.getTravel().getDestination());
    }

    public void assignNewMission(Mission currentMission, Travel travel){
        Mission newMission = getRandomMission(
                travel,
                currentMission.getTravelMissionSample().getMissionCategory(),
                currentMission.getTravel().getDestination()).orElseThrow(()-> new BusinessException(ErrorCode.NOT_FOUND));
    }
    /**
     * 현재 미션을 중지합니다.
     * @param mission 중지할 미션
     */
    private void stopCurrentMission(Mission mission) {
        missionStatusFacade.stopMission(mission);
        missionRepository.save(mission);
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

    /**
     * 주어진 미션 카테고리가 일일 미션인지 확인합니다.
     * @param category 미션 카테고리
     * @return 일일 미션이면 true, 아니면 false
     */
    private boolean isDailyMission(MissionCategory category) {
        return category == MissionCategory.TOUR || category == MissionCategory.FOOD;
    }

    /**
     * 주어진 미션이 오늘 완료되었는지 확인합니다.
     * @param mission 확인할 미션
     * @return 오늘 완료되었으면 true, 아니면 false
     */
    private boolean isMissionCompletedToday(Mission mission) {
        LocalDate today = LocalDate.now();
        return mission.getCompleteDate() != null && mission.getCompleteDate().toLocalDate().isEqual(today);
    }



}
