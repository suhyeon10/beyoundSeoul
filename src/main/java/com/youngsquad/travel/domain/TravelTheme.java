package com.youngsquad.travel.domain;

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
@Table(name = "TRAVEL_THEME")
public class TravelTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAVEL_THEME_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ROUTE")
    private String route;
}
