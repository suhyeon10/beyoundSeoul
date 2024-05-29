package com.youngsquad.record.application;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.common.s3.S3Service;
import com.youngsquad.mission.domain.Mission;
import com.youngsquad.record.domain.Record;
import com.youngsquad.record.domain.service.RecordRepository;
import com.youngsquad.record.presentation.request.CreateRecordRequest;
import com.youngsquad.travel.domain.service.mission.MissionRepository;
import com.youngsquad.user.application.UserService;
import com.youngsquad.user.domain.model.User;
import com.youngsquad.user.domain.model.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RecordCreateService {
    private final MissionRepository missionRepository;
    private final RecordRepository recordRepository;
    private final UserService userService;
    private final S3Service s3Service;
    public void create(long missionId, long uid, String recordComment, MultipartFile recordImage) throws IOException {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(()-> new BusinessException(ErrorCode.MISSION_NOT_FOUND));

        User user = userService.findUser(uid);
        Record record = Record.makeEntity(recordComment,
                uploadRecordImage(recordImage),
                mission,
                user);
        recordRepository.save(record);

    }

    public String uploadRecordImage(MultipartFile recordImage) throws IOException {
        return s3Service.upload(recordImage, "record/");
    }

}
