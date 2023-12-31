package com.youngsquad.travel.domain;

import com.youngsquad.user.domain.User;
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
@Table(name = "TRAVEL_PARTICIPATE")
public class TravelParticipate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAVEL_PARTICIPATE_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "TRAVEL_ID")
    private Travel travel;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User teamMember;
    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private TeamMemberRole teamMemberRole;

    public static TravelParticipate from(User user, Travel travel, TeamMemberRole teamMemberRole){
        return TravelParticipate.builder()
                .teamMember(user)
                .travel(travel)
                .teamMemberRole(teamMemberRole)
                .build();

    }
}
