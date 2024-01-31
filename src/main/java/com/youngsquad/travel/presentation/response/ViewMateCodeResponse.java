package com.youngsquad.travel.presentation.response;

import com.youngsquad.travel.domain.model.TravelMateCode;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ViewMateCodeResponse {

    private long expiredTime;
    private String code;

    public static ViewMateCodeResponse from(TravelMateCode travelMateCode){
        return ViewMateCodeResponse.builder()
                .expiredTime(calExpireMinute(travelMateCode))
                .code(travelMateCode.getMateCode())
                .build();
    }

    private static long calExpireMinute(TravelMateCode travelMateCode){
        return Duration.between(LocalDateTime.now(), travelMateCode.getExpireTime()).toMinutes();
    }


}

