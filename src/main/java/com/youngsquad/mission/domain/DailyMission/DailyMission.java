package com.youngsquad.mission.domain.DailyMission;

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
@Table(name = "DAILY_MISSION")
public class DailyMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DAILY_MISSION_ID")
    private Long id;
    @Column(name = "DAILY_MISSION_NAME")
    private String name;
    @Column(name = "DAILY_MISSION_DESTINATION")
    private String destination;
    @Column(name = "DAILY_MISSION_DETAIL")
    private String detail;
    @Column(name = "DAILY_MISSION_MONTH")
    private String month;
    @Column(name = "DAILY_MISSION_ADRESS")
    private String address;


}
