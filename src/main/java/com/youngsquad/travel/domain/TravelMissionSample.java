package com.youngsquad.travel.domain;

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
    @ManyToOne
    @JoinColumn(name= "TRAVEL_THEME_DESTINATION_ID")
    private TravelThemeDestination travelThemeDestination;
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
