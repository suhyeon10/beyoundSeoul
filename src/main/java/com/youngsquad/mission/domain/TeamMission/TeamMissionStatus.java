package com.youngsquad.mission.domain.TeamMission;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "TEAM_MISSION_STATUS")
public class TeamMissionStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_MISSION_STATUS_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "TRAVEL_TEAM_MISSION_ID")
    private TravelTeamMission travelTeamMission;
//    @ManyToOne
//    @JoinColumn(name = "TEAM_MISSION_MEMBER_ID")
//    private TeamMissionMember teamMissionMember;
    @Column(name = "TEAM_MISSION_MEMBER_ID")
    private long teamMissionMemberId;
    @Column(name = "TEAM_MISSION_STATUS")
    private String status;
    @Column(name = "CREATE_AT")
    private LocalDateTime createAt;
    @Column(name = "UPDATE_AT")
    private LocalDateTime updateAt;
}
