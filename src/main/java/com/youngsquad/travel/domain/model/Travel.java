package com.youngsquad.travel.domain.model;

import com.youngsquad.user.domain.User;
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
@Table(name = "TRAVEL")
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAVEL_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "TRAVEL_THEME_ID")
    private TravelTheme travelTheme;
    @ManyToOne
    @JoinColumn(name = "READER_ID")
    private User reader;
    @Column(name = "DESTINATION")
    private String destination;
    @Column(name = "START_DATE")
    private LocalDate startDate;
    @Column(name = "END_DATE")
    private LocalDate endDate;
    @Column(name = "PEOPLE_NUM")
    private String peopleNum;
    @Enumerated(EnumType.STRING)
    @Column(name = "TRAVEL_WITH")
    private TravelWithType travelWith;
    @Column(name = "TITLE")
    private String title;
}
