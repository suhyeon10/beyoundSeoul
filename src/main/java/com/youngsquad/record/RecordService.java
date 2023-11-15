package com.youngsquad.record;

import com.youngsquad.common.Response.ResponseService;
import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.common.s3.S3Service;
import com.youngsquad.mission.domain.DailyMission.DailyMissionStatus;
import com.youngsquad.mission.domain.DailyMission.DailyMissionStatusRepo;
import com.youngsquad.mission.domain.PersonMission.PersonMissionStatus;
import com.youngsquad.mission.domain.PersonMission.PersonMissionStatusRepo;
import com.youngsquad.mission.domain.TeamMission.TeamMissionMember;
import com.youngsquad.mission.domain.TeamMission.TeamMissionStatus;
import com.youngsquad.mission.domain.TeamMission.TeamMissionStatusRepo;
import com.youngsquad.mission.service.MissionService;
import com.youngsquad.record.domain.TravelRecord;
import com.youngsquad.record.domain.TravelRecordRepo;
import com.youngsquad.record.dto.CreateRecordReq;
import com.youngsquad.record.dto.ViewRecordRes;
import com.youngsquad.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecordService {
    private final PersonMissionStatusRepo personMissionStatusRepo;
    private final TeamMissionStatusRepo teamMissionStatusRepo;
    private final TravelRecordRepo travelRecordRepo;
    private final S3Service s3Service;
    private final MissionService missionService;
    private final TravelService travelService;

    public ViewRecordRes viewRecord(long uid){
        // 진행했던 여행들 찾기
        return ViewRecordRes.builder().travels(travelService.getTravelDetail(uid)).build();



    }
    @Transactional
    public void createRecord(long uid, CreateRecordReq createRecordReq) throws IOException {
        insertRecord(uid, createRecordReq);
        // 1) 미션 상태를 완료로 변경
        // 2) 새로운 미션 할당
        updateMissionStatus(uid, createRecordReq.getMissionType(), createRecordReq.getMissionId());


    }

    public void updateMissionStatus(long uid, String missionType, long missionStatusId){
        LocalDateTime now = LocalDateTime.now();
        if(missionType.equals("team")){
            TeamMissionStatus teamMissionStatus = teamMissionStatusRepo.getTeamMissionStatusById(missionStatusId);
            if(!teamMissionStatus.getStatus().equals("PROCEED")) throw new BusinessException(ErrorCode.MISSION_NOT_FOUND);
            teamMissionStatus.setStatus("SUCCESS");
            teamMissionStatus.setUpdateAt(now);
            teamMissionStatusRepo.save(teamMissionStatus);
            TeamMissionMember teamMissionMember = missionService.getTeamMissionMember(teamMissionStatus.getTeamMissionMemberId());

            missionService.makeTeamMission(teamMissionStatus.getTravelTeamMission().getTeamMissionDivision().getDestination(),
                    teamMissionStatus.getTravelTeamMission().getTravelDetail(),
                    uid, teamMissionMember
            );

        }else if(missionType.equals("person")){
            PersonMissionStatus personMissionStatus = personMissionStatusRepo.getPersonMissionStatusById(missionStatusId);
            if(!personMissionStatus.getStatus().equals("PROCEED")) throw new BusinessException(ErrorCode.MISSION_NOT_FOUND);
            personMissionStatus.setStatus("SUCCESS");
            personMissionStatus.setUpdateAt(now);
            personMissionStatusRepo.save(personMissionStatus);
            TeamMissionMember teamMissionMember = missionService.getTeamMissionMember(personMissionStatus.getTeamMissionMemberId());

            missionService.makePersonMission(personMissionStatus.getTravelPersonMission().getPersonMissionDivision().getDestination(),
                    personMissionStatus.getTravelPersonMission().getTravelDetail(),
                    uid,
                    teamMissionMember);

        }
    }

    public void insertRecord(long uid, CreateRecordReq createRecordReq) throws IOException {
        LocalDateTime uploadAt =LocalDateTime.now();

        String s3Key = s3Service.upload(createRecordReq.getRecordImage(), generateS3Key(uploadAt, uid, createRecordReq.getMissionId()));
        TravelRecord travelRecord = TravelRecord.builder()
                .image(s3Key)
                .travelId(createRecordReq.getTravelId())
                .missionStatusId(createRecordReq.getMissionId())
                .text(createRecordReq.getRecordComment())
                .userId(uid)
                .missionType(createRecordReq.getMissionType())
                .uploadAt(uploadAt)
                .build();

        travelRecordRepo.save(travelRecord);
    }

    public static String generateS3Key(LocalDateTime uploadAt, long uid, long missionStatusId) {
        // S3 키의 기본 형식: userId/yyyy/MM/dd/HH/mm/missionStatusId_image
        String pattern = "yyyy/MM/dd/HH/mm";
        String dateTimeFormatted = uploadAt.format(DateTimeFormatter.ofPattern(pattern));

        return String.format("%s/%s_%s",
                uid,
                dateTimeFormatted,
                missionStatusId + "_" );
    }

}
