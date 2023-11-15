package com.youngsquad.reserve.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ReserveModalRes {

    private Depart depart;
    private Arrive arrive;
    @Getter
    @Builder
    public static class Depart{
        private String destination;
        private String date;
        private int reserveNum;
        private List<Schedule> schedules;
        @Getter
        @Builder
        public static class Schedule{
            private String time;
            private String seat;
        }
    }
    @Getter
    @Builder
    public static class Arrive{
        private String destination;
        private String date;
        private int reserveNum;
        private List<Schedule> schedules;
        @Getter
        @Builder
        public static class Schedule{
            private String time;
            private String seat;
        }
    }


}
