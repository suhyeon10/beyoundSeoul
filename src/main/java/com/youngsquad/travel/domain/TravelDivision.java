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
@Table(name = "TRAVEL_DIVISION")
public class TravelDivision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAVEL_DIVISION_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "TRAVEL_DETAIL_ID")
    private TravelDetail travelDetail;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
