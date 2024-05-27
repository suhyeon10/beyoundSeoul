package com.youngsquad.user.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


public record UserModifyRequest(
        long uid,
        String nickName,
        String sex,
        LocalDate birth,
        MultipartFile image
) {
}
