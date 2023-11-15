package com.youngsquad.mission.domain;

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
@Table(name = "MISSION")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MISSION_ID")
    private Long id;
    @Column(name = "MISSION_TITLE")
    private String missionTitle;
    @Column(name = "MISSION_TYPE")
    private String missionType;
    @Column(name = "MISSION_DETAIL")
    private String missionDetail;
}
