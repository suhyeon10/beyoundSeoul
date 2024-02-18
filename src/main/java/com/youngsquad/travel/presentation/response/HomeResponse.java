package com.youngsquad.travel.presentation.response;

import com.youngsquad.travel.application.home.HomeTravelReadService;
import com.youngsquad.travel.domain.model.Travel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class HomeResponse {
    private Profile profile;
    private String travelStatus;
    private TravelResponse travelResponse;
    private Mission mission;
    private MissionStatus missionStatus;

    @Getter
    @Builder
    public static class Profile {
        private String userName;
        private String userImage;
    }

    @Getter
    @Builder
    public static class TravelResponse {
        private long travelId;
        private String travelTitle;
        private String travelDate;
        private List<String> travelFriends;
    }

    @Getter
    @Builder
    public static class Mission {
        private String foodMission;
        private String tourMission;
        private String sosoMission;
    }

    @Getter
    @Builder
    public static class MissionStatus {
        private String foodMissionStatus;
        private String tourMissionStatus;
        private String sosoMissionStatus;
    }

    public static TravelResponse makeTravelResponse(Travel travel, HomeTravelReadService homeTravelReadService) {
        List<String> travelFriendImages = homeTravelReadService.getTravelMemberImageList(travel);
        return TravelResponse.builder()
                .travelId(travel.getId())
                .travelTitle(travel.getTitle())
                .travelDate(formatDateRange(travel.getStartDate(), travel.getEndDate()))
                .travelFriends(travelFriendImages)
                .build();
    }

    private static String formatDateRange(LocalDate startDate, LocalDate endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMdd");
        String formattedStartDate = startDate.format(formatter);
        String formattedEndDate = endDate.format(formatter);
        return formattedStartDate + " ~ " + formattedEndDate;
    }
}
