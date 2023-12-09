package com.youngsquad.mission.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MISSION_STATUS")
public class MissionStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MISSION_STATUS_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "MISSION_ID")
    private Mission mission;
    @Column(name = "MISSION_STATUS")
    @Enumerated(EnumType.STRING)
    private MissionStatusType missionStatusType;
    @Column(name = "MODIFY_DATE")
    private LocalDateTime modifyDate;
}
