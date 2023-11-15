package com.youngsquad.record.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ViewRecordRes {
    private List<Travel> travels;
    @Getter
    @Builder
    public static class Travel{

        private String title;
        private List<Record> records;

        @Getter
        @Builder
        public static class Record{

            private String image;
            private String uploadAt;
            private String comment;
            private String missionType;
            private String missionTitle;
        }
    }
}
