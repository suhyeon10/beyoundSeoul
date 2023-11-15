package com.youngsquad.travel.service;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.common.s3.S3Service;
import com.youngsquad.mission.domain.PersonMission.PersonMissionStatus;
import com.youngsquad.mission.domain.PersonMission.PersonMissionStatusRepo;
import com.youngsquad.mission.domain.TeamMission.TeamMissionMember;
import com.youngsquad.mission.domain.TeamMission.TeamMissionMemberRepo;
import com.youngsquad.mission.domain.TeamMission.TeamMissionStatus;
import com.youngsquad.mission.domain.TeamMission.TeamMissionStatusRepo;
import com.youngsquad.record.domain.TravelRecord;
import com.youngsquad.record.domain.TravelRecordRepo;
import com.youngsquad.record.dto.ViewRecordRes;
import com.youngsquad.travel.domain.TravelDetail;
import com.youngsquad.travel.domain.TravelDetailRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TravelService {
    private final TeamMissionStatusRepo teamMissionStatusRepo;
    private final PersonMissionStatusRepo personMissionStatusRepo;

    private final TeamMissionMemberRepo teamMissionMemberRepo;
    private final TravelRecordRepo travelRecordRepo;
    private final TravelDetailRepo travelDetailRepo;
    private final S3Service s3Service;



    public List<ViewRecordRes.Travel> getTravelDetail(long uid){
        List<TeamMissionMember> teamMissionMemberList = teamMissionMemberRepo.getTeamMissionMemberByMemberId(uid);
        return teamMissionMemberList.stream().map(member -> toTravel(uid, member)).collect(Collectors.toList());
    }

    public ViewRecordRes.Travel toTravel(long uid, TeamMissionMember member){
        return ViewRecordRes.Travel.builder().title(member.getTravelDetail().getTitle())
                .records(makeRecordList(member.getTravelDetail().getId(), uid))
                .build();
    }

    public List<ViewRecordRes.Travel.Record> makeRecordList(long travelId, long uid){

        List<TravelRecord> travelRecords = travelRecordRepo.getTravelRecordsByTravelIdAndUserId(travelId, uid);
        return travelRecords.stream().map(this::makeRecord).collect(Collectors.toList());
    }

    public ViewRecordRes.Travel.Record makeRecord(TravelRecord travelRecord){
        return ViewRecordRes.Travel.Record.builder()
                .image(s3Service.getDownloadPresignedURL(travelRecord.getImage()))
                .uploadAt(makeUploadAt(travelRecord.getUploadAt()))
                .comment(travelRecord.getText())
                .missionType(makeMissionType(travelRecord.getMissionType()))
                .missionTitle(makeMissionTitle(travelRecord.getMissionStatusId(),travelRecord.getMissionType()))
                .build();
    }
    public String makeUploadAt(LocalDateTime uploadAt){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String formattedDateTime = uploadAt.format(formatter);
        return formattedDateTime;
    }
    public String makeMissionType(String missionType){
        if(missionType.equals("person")) return "개인미션";
        else if(missionType.equals("team")) return "공동미션";
        else throw new BusinessException( ErrorCode.NOT_FOUND);
    }
    public String makeMissionTitle(long missionId, String missionType){
        if(missionType.equals("person")) {
            PersonMissionStatus personMissionStatus = personMissionStatusRepo.getPersonMissionStatusById(missionId);
            return personMissionStatus.getTravelPersonMission().getPersonMissionDivision().getName();
        }
        else if(missionType.equals("team")) {
            TeamMissionStatus teamMissionStatus = teamMissionStatusRepo.getTeamMissionStatusById(missionId);
            return teamMissionStatus.getTravelTeamMission().getTeamMissionDivision().getName();
        }
        else throw new BusinessException(ErrorCode.NOT_FOUND);
    }


}
