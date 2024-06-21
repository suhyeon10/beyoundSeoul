package com.youngsquad.travel.application.home;

import com.querydsl.core.Tuple;
import com.youngsquad.common.s3.S3Service;
import com.youngsquad.mission.domain.Mission;
import com.youngsquad.mission.domain.MissionStatusType;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.mission.MissionCategory;
import com.youngsquad.travel.domain.service.home.HomeMissionStatusRepository;
import com.youngsquad.travel.presentation.response.HomeViewResponse;
import com.youngsquad.user.domain.model.User;
import com.youngsquad.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class HomeViewService {

    private final UserService userService;
    private final S3Service s3Service;
    private final HomeTravelReadService homeTravelReadService;
    private final HomeMissionStatusRepository homeMissionStatusRepository;



    @Transactional
    public HomeViewResponse viewHome(long userId) {
        // 0) 유저 프로필 정보 (항상)
        User user = userService.findUser(userId);


        Travel travel = homeTravelReadService.getUserTravel(user);

        if (travel == null) {
            log.info(":::: 여행이 없음 ::::");
            return buildEmptyHomeViewResponse(user);
        }


        // 1) 미션 진행 현황 조회
        List<Tuple> missionCounts = homeMissionStatusRepository.countMissionsByStatus(travel);

        // 2) 진행 중인 미션 내용 조회
        List<Mission> ongoingMissions = homeMissionStatusRepository.findOngoingMissionTitleAndId(travel);

        // 3) 여행 친구 이미지 조회
        List<String> travelFriendImages = homeTravelReadService.getTravelMemberImageList(travel, user);

        // 4) 미션 수 카운트 조회
        int foodMissionCount = getMissionCount(missionCounts, MissionCategory.FOOD);
        int tourMissionCount = getMissionCount(missionCounts, MissionCategory.TOUR);
        int sosoMissionCount = getMissionCount(missionCounts, MissionCategory.SOSO);

        return buildHomeViewResponse(user, travel, ongoingMissions, travelFriendImages, foodMissionCount, tourMissionCount, sosoMissionCount);
    }

    private HomeViewResponse buildEmptyHomeViewResponse(User user) {
        return HomeViewResponse.builder()
                .profile(buildProfile(user))
                .travel(null)
                .ongoingMission(null)
                .missionCount(null)
                .build();
    }

    private HomeViewResponse buildHomeViewResponse(User user, Travel travel, List<Mission> ongoingMissions,
                                                   List<String> travelFriendImages, int foodMissionCount, int tourMissionCount, int sosoMissionCount) {
        return HomeViewResponse.builder()
                .profile(buildProfile(user))
                .travel(buildTravelResponse(travel, travelFriendImages))
                .ongoingMission(buildOngoingMission(ongoingMissions))
                .missionCount(buildMissionCount(foodMissionCount, tourMissionCount, sosoMissionCount))
                .build();
    }

    private HomeViewResponse.Profile buildProfile(User user) {
        return HomeViewResponse.Profile.builder()
                .userName(user != null ? user.getNickName() : null)
                .userImage(user != null ? s3Service.getDownloadPresignedURL(user.getImage()) : null)
                .build();
    }

    private HomeViewResponse.TravelResponse buildTravelResponse(Travel travel, List<String> travelFriendImages) {
        return travel != null ? HomeViewResponse.makeTravelResponse(travel, travelFriendImages) : null;
    }

    private HomeViewResponse.OngoingMission buildOngoingMission(List<Mission> ongoingMissions) {
        return ongoingMissions != null ? HomeViewResponse.fromMission(ongoingMissions) : null;
    }

    private HomeViewResponse.MissionCount buildMissionCount(int foodMissionCount, int tourMissionCount, int sosoMissionCount) {
        return HomeViewResponse.MissionCount.builder()
                .foodMissionCount(foodMissionCount)
                .tourMissionCount(tourMissionCount)
                .sosoMissionCount(sosoMissionCount)
                .build();
    }

    private int getMissionCount(List<Tuple> missionCounts, MissionCategory missionCategory) {
        for (Tuple tuple : missionCounts) {
            MissionCategory type = tuple.get(0, MissionCategory.class); // 첫 번째 요소가 MissionCategory일 것으로 가정
            MissionStatusType statusType = tuple.get(1, MissionStatusType.class); // 두 번째 요소가 MissionStatusType일 것으로 가정
            Long count = tuple.get(2, Long.class); // 세 번째 요소가 Long count일 것으로 가정

            log.info("튜플 : " + type + ", " + statusType + ", " + count);

            // 타입이 일치하는지 확인
            if (type.equals(missionCategory)) {
                return count.intValue(); // Long을 int로 캐스팅
            }
        }
        return 0; // 일치하는 미션 카테고리가 없는 경우 0 반환
    }

}
