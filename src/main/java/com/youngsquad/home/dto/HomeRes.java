package com.youngsquad.home.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class HomeRes {
    private String userName;
    private String userImage;
    private int travelId;
    private String travelName;
    private String travelDate;
    private List<String> travelFreinds;
    private List<Reservation> reservations;
    private MissionComplete missionComplete;
    private TeamMission teamMission;
    private PersonMission personMission;
    private String dailyMissionCount;
    private List<DailyMission> dailyMissions;

    @Getter
    @Builder
    public static class Reservation{
        private String destination;
        private String date;
        private String time;
    }

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
    }
}
