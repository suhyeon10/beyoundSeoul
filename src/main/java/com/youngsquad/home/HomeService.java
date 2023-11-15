package com.youngsquad.home;

import com.youngsquad.common.s3.S3Service;
import com.youngsquad.home.dto.HomeRes;
import com.youngsquad.mission.domain.TeamMission.TeamMissionMember;
import com.youngsquad.mission.domain.TeamMission.TeamMissionMemberRepo;
import com.youngsquad.mission.service.MissionService;
import com.youngsquad.record.domain.TravelRecordRepo;
import com.youngsquad.travel.domain.TravelDetail;
import com.youngsquad.travel.domain.TravelDetailRepo;
import com.youngsquad.travel.service.TravelService;
import com.youngsquad.user.domain.User;
import com.youngsquad.user.domain.UserRepo;
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

    private final MissionService missionService;
    private final TeamMissionMemberRepo teamMissionMemberRepo;
    private final TravelDetailRepo travelDetailRepo;
    private final UserRepo userRepo;
    private final S3Service s3Service;

    public HomeRes viewHome(long uid){

        //특정 유저의 여행 정보 조회

        //특정 유저의 미션 완료도

        //특정 유저의 여행의 미션 정보
        User user = userRepo.getTopById(uid);

        TeamMissionMember teamMissionMember = teamMissionMemberRepo.getTopByMemberIdOrderByIdDesc(uid).orElse(null);

        //여행 온보딩 진행 X
        if(teamMissionMember == null)
            return HomeRes.
                builder()
                .userName(user.getNickName())
                .userImage(s3Service.getDownloadPresignedURL(user.getImage()))
                .build();
        TravelDetail travelDetail =  travelDetailRepo.getTopById(teamMissionMember.getTravelId()).orElse(null);

        long travelId = travelDetail.getId();

        //여행 온보딩 진행시
        return HomeRes.builder()
                .userName(user.getNickName())
                .userImage(s3Service.getDownloadPresignedURL(user.getImage()))
                .travelId(Math.toIntExact(travelDetail.getId()))
                .travelName(travelDetail.getDestination()+" 여행중")
                .travelDate(makeTravelDate(travelDetail.getStartDate(), travelDetail.getEndDate()))
                .travelFreinds(new ArrayList<>())
                .reservations(Collections.singletonList(HomeRes.Reservation.builder()
                        .destination("부산행 / BUSAN")
                        .date(makeReservateDate(travelDetail.getStartDate()))
                        .time("AM 10:42")
                        .build()))
                .missionComplete(missionService.countComplete(uid, travelId))
                .teamMission(missionService.getTeamMissionData(uid, travelId))
                .personMission(missionService.getPersonMissionData(uid, travelId))
                .dailyMissions(missionService.getDailyMissionData(uid, travelId))
                .dailyMissionCount(missionService.countDailyMission(uid, travelId))
                .build();
    }

    public String makeReservateDate(LocalDate date){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return date.format(dateFormatter);
    }

    public String makeTravelDate(LocalDate startDate, LocalDate endDate) {
        // DateTimeFormatter를 사용하여 형식 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMdd");

        // startDate와 endDate를 문자열로 변환
        String formattedStartDate = startDate.format(formatter);
        String formattedEndDate = endDate.format(formatter);

        // "yyyy-MMdd" 형식으로 된 날짜를 "yyyy-MMdd" 형식으로 잘라서 원하는 형식으로 조합
        String formattedDateRange = formattedStartDate.substring(0, 4) +formattedStartDate.substring(4) +
                " ~ " +
                formattedEndDate.substring(0, 4)  + formattedEndDate.substring(4);

        return formattedDateRange;
    }


}
