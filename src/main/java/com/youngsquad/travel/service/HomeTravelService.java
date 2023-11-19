package com.youngsquad.travel.service;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.mission.domain.TeamMission.TeamMissionMember;
import com.youngsquad.mission.domain.TeamMission.TeamMissionMemberRepo;
import com.youngsquad.travel.domain.TravelDetail;
import com.youngsquad.travel.domain.TravelDetailRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeTravelService {

    private final TeamMissionMemberRepo teamMissionMemberRepo;
    private final TravelDetailRepo travelDetailRepo;

    public String getTravelStatus(long uid){
        TeamMissionMember teamMissionMember = getLatestTeamMissionMember(uid);
        if (teamMissionMember==null || isAfterTravel(teamMissionMember)) {
            return "AFTER_TRAVEL";
        } if (isBeforeTravelStart(teamMissionMember)) {
            return "BEFORE_TRAVEL_START";
        } if (isDuringTravel(teamMissionMember)) {
            return "DURING_TRAVEL";
        }
        throw new BusinessException(ErrorCode.NOT_FOUND);
    }
    private Boolean isAfterTravel(TeamMissionMember teamMissionMember){
        return getToday().isAfter(teamMissionMember.getTravelDetail().getEndDate());
    }

    private Boolean isBeforeTravelStart(TeamMissionMember teamMissionMember){
        return getToday().isBefore(teamMissionMember.getTravelDetail().getStartDate());
    }

    private Boolean isDuringTravel(TeamMissionMember teamMissionMember){
        return !getToday().isBefore(teamMissionMember.getTravelDetail().getStartDate()) && !getToday().isAfter(teamMissionMember.getTravelDetail().getEndDate());
    }

    private LocalDate getToday(){
        return LocalDate.now();
    }

    private TeamMissionMember getLatestTeamMissionMember(long uid) {
        return teamMissionMemberRepo.getTopByMemberIdOrderByIdDesc(uid).orElse(null);
    }

    public TravelDetail getTravelDetail(long uid){
        TeamMissionMember teamMissionMember = teamMissionMemberRepo.getTopByMemberIdOrderByIdDesc(uid).orElse(null);
        return travelDetailRepo.getTopById(teamMissionMember.getTravelId()).orElse(null);
    }

    public String makeTravelDate(LocalDate startDate, LocalDate endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMdd");
        String formattedStartDate = startDate.format(formatter);
        String formattedEndDate = endDate.format(formatter);

        return formattedStartDate.substring(0, 4) +formattedStartDate.substring(4) +
                " ~ " +
                formattedEndDate.substring(0, 4)  + formattedEndDate.substring(4);
    }
}
