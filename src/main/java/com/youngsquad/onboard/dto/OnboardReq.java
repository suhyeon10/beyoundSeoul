package com.youngsquad.onboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class OnboardReq {
    private String age;
    private String birth;
    private String lang;
    private String travelWith;
    private String role;
    private long uid;

    private LocalDate travelStartDate;
    private LocalDate travelEndDate;
    private String transport;
    private int themaId;
    private String destination;
}
