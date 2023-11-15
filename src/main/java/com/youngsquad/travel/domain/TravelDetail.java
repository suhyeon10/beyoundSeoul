package com.youngsquad.travel.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRAVEL_DETAIL")
public class TravelDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAVEL_DETAIL_ID")
    private Long id;
    @Column(name = "READER_ID")
    private Long readerId;
    @Column(name = "DESTINATION")
    private String destination;
    @Column(name = "START_DATE")
    private LocalDate startDate;
    @Column(name = "END_DATE")
    private LocalDate endDate;
    @Column(name = "TRAVEL_WITH")
    private String travelWith;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "TRANSPORT")
    private String transport;
}
