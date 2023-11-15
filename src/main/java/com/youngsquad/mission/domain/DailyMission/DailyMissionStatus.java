package com.youngsquad.mission.domain.DailyMission;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "DAILY_MISSION_STATUS")
public class DailyMissionStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DAILY_MISSION_STATUS_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="DAILY_MISSION_ID")
    private DailyMission dailyMission;
    @Column(name = "TRAVEL_DETAIL_ID")
    private long travelId;
    @Column(name = "USER_ID")
    private long userId;
    @Column(name = "DAILY_MISSION_STATUS")
    private String status;
    @Column(name = "MISSION_DATE")
    private LocalDate missionDate;
    @Column(name = "CREATE_AT")
    private LocalDateTime createAt;
    @Column(name = "UPDATE_AT")
    private LocalDateTime updateAt;
}
