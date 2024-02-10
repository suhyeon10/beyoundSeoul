package com.youngsquad.travel.domain.model.mission;

import com.youngsquad.travel.domain.model.TravelThemeDestination;
import com.youngsquad.travel.domain.model.mission.MissionCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRAVEL_MISSION_SAMPLE")
public class TravelMissionSample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAVEL_MISSION_SAMPLE_ID")
    private Long id;
    @Column(name= "TRAVEL_MISSION_DESTINATION")
    private String travelMissionDestination;
    @Column(name = "TEAM_MISSION_CATEGORY")
    @Enumerated(EnumType.STRING)
    private MissionCategory missionCategory;
    @Column(name = "TRAVEL_MISSION_TITLE")
    private String title;
    @Column(name = "TRAVEL_MISSION_DETAIL")
    private String detail;
    @Column(name = "TRAVEL_MISSION_ADDRESS")
    private String address;
    @Column(name = "TRAVEL_MISSION_LATITUDE")
    private Double latitude;
    @Column(name = "TRAVEL_MISSION_LONGITUDE")
    private Double longitude;

}
