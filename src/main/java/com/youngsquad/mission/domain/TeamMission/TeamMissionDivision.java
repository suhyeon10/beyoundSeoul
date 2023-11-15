package com.youngsquad.mission.domain.TeamMission;

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
@Table(name = "TEAM_MISSION_DIVISION")
public class TeamMissionDivision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_MISSION_DIVISION_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "TEAM_MISSION_ID")
    private TeamMission teamMission;
    @Column(name = "TEAM_MISSION_NAME")
    private String name;
    @Column(name = "TEAM_MISSION_DESTINATION")
    private String destination;
    @Lob
    @Column(name = "TEAM_MISSION_DETAIL")
    private String detail;
    @Column(name = "TEAM_MISSION_MONTH")
    private String month;
    @Column(name = "TEAM_MISSION_ADRESS")
    private String address;

}
