package com.youngsquad.travel.dto;

import com.youngsquad.travel.domain.MateCode;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ViewMateCode {

    private long expiredTime;
    private String code;

    public static ViewMateCode from(MateCode mateCode){
        return ViewMateCode.builder()
                .expiredTime(calExpireMinute(mateCode))
                .code(mateCode.getCode())
                .build();
    }

    private static long calExpireMinute(MateCode mateCode){
        return Duration.between(LocalDateTime.now(), mateCode.getExpireDateTime()).toMinutes();
    }


}

