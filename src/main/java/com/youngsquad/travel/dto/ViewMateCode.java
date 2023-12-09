package com.youngsquad.travel.dto;

import com.youngsquad.travel.domain.TravelMateCode;
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

    public static ViewMateCode from(TravelMateCode travelMateCode){
        return ViewMateCode.builder()
                .expiredTime(calExpireMinute(travelMateCode))
                .code(travelMateCode.getMateCode())
                .build();
    }

    private static long calExpireMinute(TravelMateCode travelMateCode){
        return Duration.between(LocalDateTime.now(), travelMateCode.getExpireTime()).toMinutes();
    }


}

