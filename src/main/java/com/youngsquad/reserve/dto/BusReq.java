package com.youngsquad.reserve.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class BusReq {
    private long uid;
    private String destination;
    private String airport;
    private LocalDate travelStartDate;

}
