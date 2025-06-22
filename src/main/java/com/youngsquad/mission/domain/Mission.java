package com.youngsquad.mission.domain;

import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.mission.TravelMissionSample;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MISSION")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MISSION_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "TRAVEL_ID")
    private Travel travel;
    @ManyToOne
    @JoinColumn(name = "TRAVEL_MISSION_SAMPLE_ID")
    private TravelMissionSample travelMissionSample;
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
    @Column(name = "COMPLETE_DATE")
    private LocalDateTime completeDate;

    public static Mission makeEntity(Travel travel, TravelMissionSample travelMissionSample){
        return Mission.builder()
                .createDate(LocalDateTime.now())
                .travel(travel)
                .travelMissionSample(travelMissionSample)
                .build();
    }

}
