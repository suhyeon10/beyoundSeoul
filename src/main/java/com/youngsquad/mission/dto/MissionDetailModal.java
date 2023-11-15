package com.youngsquad.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MissionDetailModal {
    private String missionTitle;
    private String missionDetail;
    private String recordImage;
    private String recordComment;
}
