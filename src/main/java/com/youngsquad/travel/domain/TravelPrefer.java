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
@Table(name = "TRAVEL_PREFER")
public class TravelPrefer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAVEL_PREFER_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @Column(name = "MISSION_COUNT")
    private Integer missionCount;
    @Column(name = "TRAVEL_CATEGORY")
    private String travelCategory;
}
