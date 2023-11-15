package com.youngsquad.mission.domain.PersonMission;

import com.youngsquad.travel.domain.TravelDetail;
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
@Table(name = "TRAVEL_PERSON_MISSION")
public class TravelPersonMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAVEL_PERSON_MISSION_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "TRAVEL_DETAIL_ID")
    private TravelDetail travelDetail;
    @ManyToOne
    @JoinColumn(name = "PERSON_MISSION_DIVISION_ID")
    private PersonMissionDivision personMissionDivision;


}
