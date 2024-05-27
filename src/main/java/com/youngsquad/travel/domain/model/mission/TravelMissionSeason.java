package com.youngsquad.travel.domain.model.mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRAVEL_MISSION_SEASON")
public class TravelMissionSeason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAVEL_MISSION_SEASON_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="TRAVEL_MISSION_SAMPLE_ID")
    private TravelMissionSample travelMissionSample;
    @Column(name = "SEASON")
    @Enumerated(EnumType.STRING)
    private MissionSeason missionSeason;
}
