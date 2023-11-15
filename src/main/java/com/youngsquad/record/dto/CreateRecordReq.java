package com.youngsquad.record.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@AllArgsConstructor
public class CreateRecordReq {
    private long uid;
    private int missionId;
    private String missionType;
    private MultipartFile recordImage;
    private String recordComment;
    private int travelId;



}
