package com.youngsquad.mission.service;

import com.youngsquad.home.dto.HomeRes;
import com.youngsquad.mission.domain.DailyMission.DailyMission;
import com.youngsquad.mission.domain.DailyMission.DailyMissionRepo;
import com.youngsquad.mission.domain.DailyMission.DailyMissionStatus;
import com.youngsquad.mission.domain.DailyMission.DailyMissionStatusRepo;
import com.youngsquad.mission.domain.PersonMission.*;
import com.youngsquad.mission.domain.TeamMission.*;
import com.youngsquad.mission.dto.MissionDetailModal;
import com.youngsquad.travel.domain.TravelDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MissionService {
    private final DailyMissionStatusRepo dailyMissionStatusRepo;
    private final TeamMissionStatusRepo teamMissionStatusRepo;
    private final TravelTeamMissionRepo travelTeamMissionRepo;
    private final PersonMissionStatusRepo personMissionStatusRepo;
    private final TravelPersonMissonRepo travelPersonMissonRepo;
    private final DailyMissionRepo dailyMissionRepo;
    private final PersonMissionDivisionRepo personMissionDivisionRepo;
    private final TeamMissionDivisionRepo teamMissionDivisionRepo;
    private final TeamMissionMemberRepo teamMissionMemberRepo;

    public void refreshMission(long uid, long missionId, String missionType){

        //1) 이전미션 종료료 변경,
        //2) 새로운 미션 할당
        if(missionType.equals("team")){
            //1) 이전미션 종료료 변경,
            TeamMissionStatus teamMissionStatus = teamMissionStatusRepo.getTeamMissionStatusById(missionId);
            teamMissionStatus.setStatus("END");
            teamMissionStatusRepo.save(teamMissionStatus);
            TeamMissionMember teamMissionMember = getTeamMissionMember(teamMissionStatus.getTeamMissionMemberId());
            //2) 새로운 미션 할당
            makeTeamMission(teamMissionStatus.getTravelTeamMission().getTeamMissionDivision().getDestination(),
                    teamMissionStatus.getTravelTeamMission().getTravelDetail(),
                    uid,
                    teamMissionMember
                    );
        }else if(missionType.equals("person")) {
            //1) 이전미션 종료료 변경,
            PersonMissionStatus personMissionStatus = personMissionStatusRepo.getPersonMissionStatusById(missionId);
            personMissionStatus.setStatus("END");
            personMissionStatusRepo.save(personMissionStatus);
            TeamMissionMember teamMissionMember = getTeamMissionMember(personMissionStatus.getTeamMissionMemberId());
            //2) 새로운 미션 할당
            makePersonMission(personMissionStatus.getTravelPersonMission().getPersonMissionDivision().getDestination(),
                    personMissionStatus.getTravelPersonMission().getTravelDetail(),
                    uid,
                    teamMissionMember);
        }
    }
    public TeamMissionMember getTeamMissionMember(long teamMemberId){
        return teamMissionMemberRepo.getTeamMissionMemberById(teamMemberId);
    }
    public MissionDetailModal getDetailModal(int uid, long missionId, String missionType){

        return MissionDetailModal.builder()
                .missionTitle("전통 한국 사우나인 찜질방을 방문하기")
                .missionDetail("한복이란? 어쩌고다~")
                .build();
    }

    public void createMission(String destination, TravelDetail travelDetail, long uid, LocalDate startDate, LocalDate endDate){
        // 여행지에 따라 미션 랜덤 추가

        TeamMissionMember teamMissionMember = insertTeamMissionMember(uid,travelDetail);
        makeTeamMission(destination, travelDetail, uid, teamMissionMember);
        makePersonMission(destination, travelDetail, uid, teamMissionMember);
        makeDailyMission(uid, travelDetail.getId(), destination, startDate, endDate);
    }

    public TeamMissionMember insertTeamMissionMember(long uid, TravelDetail travelDetail){
        TeamMissionMember teamMissionMember = TeamMissionMember.builder()
                .memberId(uid)
                .travelDetail(travelDetail)
                .travelId(travelDetail.getId())
                .role("READER")
                .build();
        teamMissionMemberRepo.save(teamMissionMember);
        return teamMissionMember;

    }

    public void makeTeamMission(String destination, TravelDetail travelDetail, long uid, TeamMissionMember teamMissionMember){
        //1) 여행_공동미션에 데이터 추가
        //2) 공동미션수행에 status 진행중으로 리더의 진행중 데이터 추가
        List<TeamMissionDivision> teamMissionDivisions = teamMissionDivisionRepo.getTeamMissionDivisionsByDestination(destination);
        Collections.shuffle(teamMissionDivisions);
        TeamMissionDivision selectedMission = teamMissionDivisions.get(0);

        TravelTeamMission travelTeamMission = TravelTeamMission.builder()
                .teamMissionDivision(selectedMission)
                .travelDetail(travelDetail)
                .build();
        travelTeamMissionRepo.save(travelTeamMission);

        TeamMissionStatus teamMissionStatus = TeamMissionStatus.builder()
                .status("PROCEED")
                .teamMissionMemberId(teamMissionMember.getId())
                .travelTeamMission(travelTeamMission)
                .createAt(LocalDateTime.now())
                .build();

        teamMissionStatusRepo.save(teamMissionStatus);

    }
    public void makePersonMission(String destination, TravelDetail travelDetail, long uid, TeamMissionMember teamMissionMember){
        //1) 여행_개인미션에 데이터 추가
        List<PersonMissionDivision> personMissionDivisions = personMissionDivisionRepo.getPersonMissionDivisionsByDestination(destination);
        Collections.shuffle(personMissionDivisions);
        PersonMissionDivision selectedMission = personMissionDivisions.get(0);

        TravelPersonMission travelPersonMission = TravelPersonMission.builder()
                .personMissionDivision(selectedMission)
                .travelDetail(travelDetail)
                .build();
        travelPersonMissonRepo.save(travelPersonMission);

        //2) 개인미션수행에 status 진행중으로 리더의 진행중 데이터 추가
        PersonMissionStatus personMissionStatus = PersonMissionStatus.builder()
                .status("PROCEED")
                .teamMissionMemberId(teamMissionMember.getId())
                .travelPersonMission(travelPersonMission)
                .createAt(LocalDateTime.now())
                .build();
        personMissionStatusRepo.save(personMissionStatus);

    }
    public void makeDailyMission(long uid, long travelId, String destination, LocalDate startDate, LocalDate endDate){
        List<DailyMissionStatus> dailyMissionStatuses = new ArrayList<>();
        //여행 시작~종료 일자까지 하루마다 일일미션을 3개씩 만들어두기
        int daysCount = (int) startDate.until(endDate.plusDays(1)).getDays();

        List<DailyMission> dailyMissionList = dailyMissionRepo.getDailyMissionsByDestination(destination);
        // 각 날짜에 데일리 미션 할당
        for (int i = 0; i < daysCount; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            Collections.shuffle(dailyMissionList);
            List<DailyMission> selectedMissions = dailyMissionList.subList(0, 3);
            // 각 날짜에 데일리 미션 할당
            List<DailyMissionStatus> statuses = selectedMissions.stream().map(
                    mission -> makeDailyStatusList(uid, travelId, mission, currentDate)
            ).collect(Collectors.toList());
            dailyMissionStatuses.addAll(statuses);

        }
        dailyMissionStatusRepo.saveAll(dailyMissionStatuses);

    }

    public DailyMissionStatus makeDailyStatusList(long uid, long travelId, DailyMission dailyMission, LocalDate currentDate){
        return DailyMissionStatus.builder()
                .travelId(travelId)
                .missionDate(currentDate)
                .dailyMission(dailyMission)
                .status("PROCEED")
                .userId(uid)
                .createAt(LocalDateTime.now())
                .build();
    }

    public void updateDailyMissionStatus(long missionStatusId){
        DailyMissionStatus dailyMissionStatus = dailyMissionStatusRepo.getDailyMissionStatusById(missionStatusId);
        dailyMissionStatus.setStatus("SUCCESS");
    }

}


