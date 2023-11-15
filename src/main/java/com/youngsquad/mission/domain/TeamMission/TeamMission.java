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
@Table(name = "TEAM_MISSION")
public class TeamMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_MISSION_ID")
    private Long id;
    @Column(name = "TOTAL_NUM")
    private Integer totalNum;
    @Column(name = "TOTAL_FULL")
    private Character totalFull;
}
