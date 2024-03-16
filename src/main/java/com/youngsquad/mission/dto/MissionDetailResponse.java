package com.youngsquad.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MissionDetailResponse {
    private String title;
    private String detail;
    private String location;
    private double lat;
    private double lon;

}
