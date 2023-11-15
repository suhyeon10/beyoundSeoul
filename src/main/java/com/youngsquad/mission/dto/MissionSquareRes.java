package com.youngsquad.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MissionSquareRes {
    private String destination;
    private List<Mission> missions;
    @Getter
    @Builder
    public static class Mission{
        private String readerThumb;
        private String sex;
        private String age;
        private String comment;
        private int totalNum;
        List<String> memImages;
    }
}
