package com.youngsquad.mission.service;

import com.youngsquad.home.dto.HomeRes;
import com.youngsquad.mission.domain.DailyMission.DailyMissionStatus;
import com.youngsquad.mission.domain.DailyMission.DailyMissionStatusRepo;
import com.youngsquad.mission.domain.PersonMission.PersonMissionStatus;
import com.youngsquad.mission.domain.PersonMission.PersonMissionStatusRepo;
import com.youngsquad.mission.domain.TeamMission.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeMissionService {
    private final DailyMissionStatusRepo dailyMissionStatusRepo;
    private final TeamMissionStatusRepo teamMissionStatusRepo;
    private final PersonMissionStatusRepo personMissionStatusRepo;
    private final TeamMissionMemberRepo teamMissionMemberRepo;
    public HomeRes.Mission.MissionComplete countComplete(long uid, long travelId){

        LocalDate today = LocalDate.now();
        // 팀미션 완료 총 개수
        TeamMissionMember missionMember = teamMissionMemberRepo.getFirstByTravelIdAndMemberId(travelId, uid);
        List<TeamMissionStatus> teamMissionStatuses = teamMissionStatusRepo.getTeamMissionStatusesByTeamMissionMemberIdAndStatus(missionMember.getId(), "SUCCESS");
        int teamMissionSuccessCount = teamMissionStatuses.size();

        //개인미션 완료 총개수
        List<PersonMissionStatus> personMissionStatuses = personMissionStatusRepo.getPersonMissionStatusesByTeamMissionMemberIdAndStatus(missionMember.getId(), "SUCCESS");
        int personMissionSuccessCount = personMissionStatuses.size();


        //일일미션 총 개수
        List<DailyMissionStatus> dailyMissionStatuses = dailyMissionStatusRepo.getDailyMissionStatusesByTravelIdAndUserIdAndStatus(travelId, uid, "SUCCESS");
        int dailySuccessCount = dailyMissionStatuses.size();

        return HomeRes.Mission.MissionComplete.builder()
                .person(personMissionSuccessCount)
                .team(teamMissionSuccessCount)
                .daily(personMissionSuccessCount)
                .build();

    }

    public HomeRes.Mission.TeamMission getTeamMissionData(long uid, long travelId){

        // 공동미션팀원에서 회원id, 여행id로 검색후, 공동미션수행에서 공동미션팀원id로 검색후, 진행중인것들중 하나만 셀렉
        TeamMissionMember missionMember = teamMissionMemberRepo.getFirstByTravelIdAndMemberId(travelId, uid);
        log.info("travelid :: "+travelId);
        log.info("missionMember.getMemberId() :: "+missionMember.getMemberId());

        TeamMissionStatus status = teamMissionStatusRepo.getTeamMissionStatusByTeamMissionMemberIdAndStatus(missionMember.getId(), "PROCEED");
        return HomeRes.Mission.TeamMission.builder()
                .id(Math.toIntExact(status.getId()))
                .title(status.getTravelTeamMission().getTeamMissionDivision().getName())
                .detail(status.getTravelTeamMission().getTeamMissionDivision().getDetail())
                .address(status.getTravelTeamMission().getTeamMissionDivision().getAddress())
                .build();
    }
    public HomeRes.Mission.PersonMission getPersonMissionData(long uid, long travelId){
        // 공동미션팀원에서 회원id, 여행id로 검색후, 공동미션수행에서 공동미션팀원id로 검색후, 진행중인것들중 하나만 셀렉
        TeamMissionMember missionMember = teamMissionMemberRepo.getFirstByTravelIdAndMemberId(travelId, uid);
        PersonMissionStatus status = personMissionStatusRepo.getFirstByTeamMissionMemberIdAndStatus(missionMember.getId(), "PROCEED");
        return HomeRes.Mission.PersonMission.builder()
                .id(Math.toIntExact(status.getId()))
                .title(status.getTravelPersonMission().getPersonMissionDivision().getName())
                .detail(status.getTravelPersonMission().getPersonMissionDivision().getDetail())
                .address(status.getTravelPersonMission().getPersonMissionDivision().getAddress())
                .build();
    }

    public List<HomeRes.Mission.DailyMission> getDailyMissionData(long uid, long travelId){
        LocalDate today = LocalDate.now();
        //일일미션수행에서 여행id와, 회원id로 수행중인 미션 찾기
        List<DailyMissionStatus> dailyMissionStatuses = dailyMissionStatusRepo.getDailyMissionStatusesByTravelIdAndUserIdAndStatus(travelId, uid, "PROCEED");
        dailyMissionStatuses.sort((status1, status2) -> status2.getCreateAt().compareTo(status1.getCreateAt()));

        if(dailyMissionStatuses.size()>0){
            List<DailyMissionStatus> todayDailyMissions = dailyMissionStatuses.subList(0,3);
            List<HomeRes.Mission.DailyMission> dailyMissions = todayDailyMissions.stream().map(this::toDailyMission).collect(Collectors.toList());
            return dailyMissions;
        }
        return new ArrayList<>();

    }

    private HomeRes.Mission.DailyMission toDailyMission(DailyMissionStatus status){
        return HomeRes.Mission.DailyMission.builder()
                .title(status.getDailyMission().getName())
                .id(Math.toIntExact(status.getId()))
                .status(status.getStatus())
                .build();
    }

    public String countDailyMission(long uid, long travelId){
        LocalDate today = LocalDate.now();
        // 오늘의 일일미션 개수 {완료한수}/총수
        List<DailyMissionStatus> dailyMissionStatuses = dailyMissionStatusRepo.getDailyMissionStatusesByTravelIdAndUserIdAndMissionDate(travelId, uid, today);

        int successCount = 0;
        for (DailyMissionStatus status : dailyMissionStatuses) {
            if ("SUCCESS".equals(status.getStatus())) {
                successCount++;
            }
        }
        String msg = successCount+"/3";
        return msg;
    }
}
