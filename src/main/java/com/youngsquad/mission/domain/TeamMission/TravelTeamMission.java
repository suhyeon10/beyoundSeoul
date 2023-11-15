package com.youngsquad.mission.domain.TeamMission;

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
@Table(name = "TRAVEL_TEAM_MISSION")
public class TravelTeamMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAVEL_TEAM_MISSION_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "TRAVEL_DETAIL_ID")
    private TravelDetail travelDetail;
    @ManyToOne
    @JoinColumn(name = "TEAM_MISSION_DIVISION_ID")
    private TeamMissionDivision teamMissionDivision;


}
