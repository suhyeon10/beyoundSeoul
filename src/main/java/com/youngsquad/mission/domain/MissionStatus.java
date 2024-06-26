package com.youngsquad.mission.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
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
    @OneToOne
    @JoinColumn(name = "MISSION_ID")
    private Mission mission;
    @Column(name = "MISSION_STATUS")
    @Enumerated(EnumType.STRING)
    private MissionStatusType missionStatusType;
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
    @Column(name = "MODIFY_DATE")
    private LocalDateTime modifyDate;

    public static MissionStatus makeEntity(Mission mission){
        return MissionStatus.builder()
                .mission(mission)
                .missionStatusType(MissionStatusType.ONGOING)
                .createDate(LocalDateTime.now())
                .build();
    }
    public void stop(){

        this.missionStatusType = MissionStatusType.STOP;
    }
    public void end(){

        this.missionStatusType = MissionStatusType.END;
    }

}
