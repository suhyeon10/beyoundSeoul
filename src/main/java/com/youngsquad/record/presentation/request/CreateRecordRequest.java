package com.youngsquad.record.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;


public record CreateRecordRequest(
         long uid,
         long missionId,
         MultipartFile recordImage,
         String recordComment
) {

}
