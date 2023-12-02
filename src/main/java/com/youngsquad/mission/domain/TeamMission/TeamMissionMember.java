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
@Table(name = "TEAM_MISSION_MEMBER")
public class TeamMissionMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_MISSION_MEMBER_ID")
    private Long id;
    @Column(name = "MEMBER_ID")
    private Long memberId;
    @Column(name = "ROLE")
    private String role;
    @Column(name = "TRAVEL_ID")
    private Long travelId;
    @ManyToOne
    @JoinColumn(name = "TRAVEL_DETAIL")
    private TravelDetail travelDetail;

    public static TeamMissionMember to(long memberId, TravelDetail travelDetail){
        return TeamMissionMember.builder()
                .memberId(memberId)
                .travelDetail(travelDetail)
                .travelId(travelDetail.getId())
                .role("TEAM")
                .build();
    }
}
