package com.youngsquad.travel.presentation.response;

import com.querydsl.core.Tuple;
import com.youngsquad.mission.domain.Mission;
import com.youngsquad.travel.application.home.HomeTravelReadService;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.mission.MissionCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class HomeViewResponse {
    private Profile profile;
    private TravelResponse travel;
    private OngoingMission ongoingMission;
    private MissionCount missionCount;

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
    @Setter
    @Builder
    //진행 중인 미션의 내용
    public static class OngoingMission {
        private int foodMissionId;
        private String foodMission;
        private int tourMissionId;
        private String tourMission;
        private int sosoMissionId;
        private String sosoMission;
    }

    @Getter
    @Builder
    //진행 완료한 미션의 수
    public static class MissionCount {
        private int foodMissionCount;
        private int tourMissionCount;
        private int sosoMissionCount;
    }



    public static OngoingMission fromMission(List<Mission> missions) {
        OngoingMission.OngoingMissionBuilder builder = OngoingMission.builder();

        for (Mission mission : missions) {
            if (MissionCategory.FOOD.equals(mission.getTravelMissionSample().getMissionCategory())) {
                builder.foodMission(mission.getTravelMissionSample().getTitle())
                        .foodMissionId(mission.getId().intValue());
            } else if (MissionCategory.TOUR.equals(mission.getTravelMissionSample().getMissionCategory())) {
                builder.tourMission(mission.getTravelMissionSample().getTitle())
                        .tourMissionId(mission.getId().intValue());
            } else if (MissionCategory.SOSO.equals(mission.getTravelMissionSample().getMissionCategory())) {
                builder.sosoMission(mission.getTravelMissionSample().getTitle())
                        .sosoMissionId(mission.getId().intValue());
            }
        }

        return builder.build();
    }

    public static TravelResponse makeTravelResponse(Travel travel, List<String> travelFriendImages) {
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
