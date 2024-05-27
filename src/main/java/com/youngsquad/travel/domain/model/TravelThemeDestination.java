package com.youngsquad.travel.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRAVEL_THEME_DESTINATION")
public class TravelThemeDestination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAVEL_THEME_DESTINATION_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="TRAVEL_THEME_ID")
    private TravelTheme travelTheme;
    @Column(name = "DESTINATION")
    private String destination;
    @Column(name = "ROUTE")
    private String route;
    @Column(name = "DETAIL")
    private String detal;

}
