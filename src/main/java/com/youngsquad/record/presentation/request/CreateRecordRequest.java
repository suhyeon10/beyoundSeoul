package com.youngsquad.record.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@AllArgsConstructor
public class CreateRecordRequest {
    private long uid;
    private int missionId;
    private String missionType;
    private MultipartFile recordImage;
    private String recordComment;
    private int travelId;



}
