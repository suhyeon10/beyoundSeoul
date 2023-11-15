package com.youngsquad.mission.domain;

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
@Table(name = "MISSION_DIVISION")
public class MissionDivision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MISSION_DIVISION_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "MISSION_ID")
    private Mission mission;
    @ManyToOne
    @JoinColumn(name = "TRAVEL_DETAIL_ID")
    private TravelDetail travelDetail;
    @Column(name = "STATUS")
    private String status;
}
