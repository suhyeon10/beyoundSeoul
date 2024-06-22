package com.youngsquad.travel.presentation.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaderOnboardRequest extends OnboardRequest{
    private String role;
    private LocalDate travelStartDate;
    private LocalDate travelEndDate;
    private int themaId;
    private String destination;
}

