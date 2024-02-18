package com.youngsquad.travel.application.home;

import com.querydsl.core.Tuple;
import com.youngsquad.common.s3.S3Service;
import com.youngsquad.mission.domain.Mission;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.service.HomeMissionStatusRepository;
import com.youngsquad.travel.presentation.response.HomeViewResponse;
import com.youngsquad.user.domain.model.User;
import com.youngsquad.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class HomeViewService {

    private final UserService userService;
    private final S3Service s3Service;
    private final HomeTravelReadService homeTravelReadService;
    private final HomeMissionStatusRepository homeMissionStatusRepository;


    public HomeViewResponse viewHome(long userId) {
        // 0) 유저 프로필 정보 (항상)
        User user = userService.findUser(userId);
        Travel travel = homeTravelReadService.getUserTravel(user);

        if (travel == null) {
            return buildEmptyHomeViewResponse(user);
        }

        // 1) 미션 진행 현황 조회
        List<Tuple> missionCounts = homeMissionStatusRepository.countMissionsByStatus(travel);

        // 2) 진행 중인 미션 내용 조회
        List<Mission> ongoingMissions = homeMissionStatusRepository.findOngoingMissionTitleAndId(travel);

        // 3) 여행 친구 이미지 조회
        List<String> travelFriendImages = homeTravelReadService.getTravelMemberImageList(travel, user);

        // 4) 미션 수 카운트 조회
        int foodMissionCount = getMissionCount(missionCounts, "FOOD");
        int tourMissionCount = getMissionCount(missionCounts, "TOUR");
        int sosoMissionCount = getMissionCount(missionCounts, "SOSO");

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

    private int getMissionCount(List<Tuple> missionCounts, String missionType) {
        for (Tuple tuple : missionCounts) {
            String type = tuple.get(0, String.class);
            int count = tuple.get(1, Long.class).intValue();
            if (type.equals(missionType)) {
                return count;
            }
        }
        return 0; // 해당 미션 유형의 개수가 없을 경우
    }
}
