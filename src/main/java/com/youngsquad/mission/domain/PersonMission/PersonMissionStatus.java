package com.youngsquad.mission.domain.PersonMission;

import com.youngsquad.mission.domain.TeamMission.TravelTeamMission;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "PERSON_MISSION_STATUS")
public class PersonMissionStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSON_MISSION_STATUS_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "TRAVEL_PERSON_MISSION_ID")
    private TravelPersonMission travelPersonMission;
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
