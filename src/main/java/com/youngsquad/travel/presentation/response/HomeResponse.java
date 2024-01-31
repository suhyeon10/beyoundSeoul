package com.youngsquad.travel.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class HomeResponse {
    private Profile profile;
    private String travelStatus;
    private Travel travel;
    private List<Reservation> reservations;
    private Mission mission;

    @Getter
    @Builder
    public static class Profile{
        private String userName;
        private String userImage;
    }
    @Getter
    @Builder
    public static class Travel{
        private int travelId;
        private String travelName;
        private String travelDate;
        private List<String> travelFreinds;

    }
    @Getter
    @Builder
    public static class Mission{
        private MissionComplete missionComplete;
        private TeamMission teamMission;
        private PersonMission personMission;
        private String dailyMissionCount;
        private List<DailyMission> dailyMissions;

        @Getter
        @Builder
        public static class MissionComplete{
            private int team;
            private int person;
            private int daily;
        }
        @Getter
        @Builder
        public static class TeamMission{
            private int id;
            private String title;
            private String detail;
            private String address;
        }

        @Getter
        @Builder
        public static class PersonMission{
            private int id;
            private String title;
            private String detail;
            private String address;

        }
        @Getter
        @Builder
        public static class DailyMission{
            private int id;
            private String title;
            private String status;
        }
    }

    @Getter
    @Builder
    public static class Reservation{
        private String destination;
        private String date;
        private String time;
    }



}
