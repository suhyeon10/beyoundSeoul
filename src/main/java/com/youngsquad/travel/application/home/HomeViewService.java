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

    public HomeViewResponse viewHome(long userId){
        // 0) 유저 프로필 정보 (항상)
        User user = userService.findUser(userId);
        Travel travel = homeTravelReadService.getUserTravel(user);

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

        return HomeViewResponse.builder()
                .profile(ofProfile(user))
                .travel(HomeViewResponse.makeTravelResponse(travel, travelFriendImages))
                .ongoingMission(HomeViewResponse.fromMission(ongoingMissions))
                .missionCount(ofMissionCount(foodMissionCount, tourMissionCount, sosoMissionCount))
                .build();
    }

    private HomeViewResponse.Profile ofProfile(User user){
        return HomeViewResponse.Profile.builder()
                .userName(user.getNickName())
                .userImage(s3Service.getDownloadPresignedURL(user.getImage()))
                .build();
    }

    private HomeViewResponse.MissionCount ofMissionCount(int foodMissionCount, int tourMissionCount, int sosoMissionCount) {
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
