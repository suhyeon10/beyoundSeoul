package com.youngsquad.home;

import com.youngsquad.common.s3.S3Service;
import com.youngsquad.home.dto.HomeRes;
import com.youngsquad.mission.domain.TeamMission.TeamMissionMember;
import com.youngsquad.mission.domain.TeamMission.TeamMissionMemberRepo;
import com.youngsquad.mission.service.HomeMissionService;
import com.youngsquad.mission.service.MissionService;
import com.youngsquad.record.domain.TravelRecordRepo;
import com.youngsquad.travel.domain.TravelDetail;
import com.youngsquad.travel.domain.TravelDetailRepo;
import com.youngsquad.travel.service.HomeTravelService;
import com.youngsquad.travel.service.TravelService;
import com.youngsquad.user.domain.User;
import com.youngsquad.user.domain.UserRepo;
import com.youngsquad.user.service.HomeUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeService {

    private final HomeUserService homeUserService;
    private final S3Service s3Service;
    private final HomeMissionService homeMissionService;
    private final HomeTravelService homeTravelService;

    public HomeRes viewHome(long uid){

        // 0) 유저 프로필 정보 (항상)
        User user = homeUserService.getUser(uid);

        // 1) 여행 정보        // 2) 미션 정보
        TravelDetail travelDetail =  homeTravelService.getTravelDetail(uid);
        long travelId = travelDetail.getId();

        return HomeRes.builder()
                .profile(ofProfile(user))
                .travelStatus(homeTravelService.getTravelStatus(uid))
                .travel(ofTravel(travelDetail))
//                .mission(ofMission(uid, travelId))
                .build();
    }

    public HomeRes.Profile ofProfile(User user){
        return HomeRes.Profile.builder()
                .userName(user.getNickName())
                .userImage(s3Service.getDownloadPresignedURL(user.getImage()))
                .build();
    }

    public HomeRes.Travel ofTravel(TravelDetail travelDetail){
        return HomeRes.Travel.builder()
                .travelId(Math.toIntExact(travelDetail.getId()))
                .travelName(travelDetail.getDestination()+" 여행중")
                .travelDate(homeTravelService.makeTravelDate(travelDetail.getStartDate(), travelDetail.getEndDate()))
                .travelFreinds(new ArrayList<>())
                .build();
    }

    public HomeRes.Mission ofMission(long uid, long travelId){
        return HomeRes.Mission.builder()
                .missionComplete(homeMissionService.countComplete(uid, travelId))
                .teamMission(homeMissionService.getTeamMissionData(uid, travelId))
                .personMission(homeMissionService.getPersonMissionData(uid, travelId))
                .dailyMissions(homeMissionService.getDailyMissionData(uid, travelId))
                .dailyMissionCount(homeMissionService.countDailyMission(uid, travelId))
                .build();
    }


    public String makeReservateDate(LocalDate date){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return date.format(dateFormatter);
    }


}
