package com.youngsquad.travel.dto;

import com.youngsquad.travel.domain.MateCode;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ViewMateCode {

    private LocalDateTime expiredTime;
    private String code;

    public static ViewMateCode from(MateCode mateCode){
        return ViewMateCode.builder()
                .expiredTime(mateCode.getExpireDateTime())
                .code(mateCode.getCode())
                .build();
    }

}

