package com.youngsquad.travel.domain.model;

import com.youngsquad.travel.presentation.request.OnboardRequest;
import com.youngsquad.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @Column(name = "TRAVEL_THEME_ID")
    private long travelTheme;
    @Column(name = "READER_ID")
    private long reader;
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
    private LocalDateTime createAt;

    public static Travel makeEntity(String destination, LocalDate endDate, LocalDate startDate, long uid, TravelWithType travelWith, String title, long themeId){
        return Travel.builder()
                .destination(destination)
                .endDate(endDate)
                .startDate(startDate)
                .reader(uid)
                .travelWith(travelWith)
                .title(title)
                .travelTheme(themeId)
                .createAt(LocalDateTime.now())
                .build();
    }
}
