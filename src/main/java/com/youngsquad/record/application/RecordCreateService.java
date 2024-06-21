package com.youngsquad.record.application;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.common.s3.S3Service;
import com.youngsquad.mission.application.MissionStatusFacade;
import com.youngsquad.mission.domain.Mission;
import com.youngsquad.record.domain.Record;
import com.youngsquad.record.domain.service.RecordRepository;
import com.youngsquad.travel.domain.service.mission.MissionRepository;
import com.youngsquad.user.application.UserService;
import com.youngsquad.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RecordCreateService {
    private final MissionRepository missionRepository;
    private final RecordRepository recordRepository;
    private final MissionStatusFacade missionStatusFacade;
    private final UserService userService;
    private final S3Service s3Service;
    @Transactional
    public void create(long missionId, long uid, String recordComment, MultipartFile recordImage) throws IOException {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(()-> new BusinessException(ErrorCode.MISSION_NOT_FOUND));

        User user = userService.findUser(uid);

        // 기록 생성
        Record record = Record.makeEntity(recordComment,
                uploadRecordImage(recordImage),
                mission,
                user);
        recordRepository.save(record);
        // 미션 종료
        missionStatusFacade.endMission(mission);

    }

    public String uploadRecordImage(MultipartFile recordImage) throws IOException {
        return s3Service.upload(recordImage, "record/");
    }



}
