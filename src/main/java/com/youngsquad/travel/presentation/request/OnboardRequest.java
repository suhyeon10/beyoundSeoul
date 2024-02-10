package com.youngsquad.travel.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OnboardRequest {
    private String age;
    private String birth;
    private String lang;
    private String travelWith;
    private String role;
    private String sex;
    private int uid;

    private LocalDate travelStartDate;
    private LocalDate travelEndDate;
    private int themaId;
    private String destination;
}
