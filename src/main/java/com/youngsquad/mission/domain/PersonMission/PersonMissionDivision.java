package com.youngsquad.mission.domain.PersonMission;

import com.youngsquad.mission.domain.TeamMission.TeamMission;
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
@Table(name = "PERSON_MISSION_DIVISION")
public class PersonMissionDivision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSON_MISSION_DIVISION_ID")
    private Long id;
    @Column(name = "PERSON_MISSION_NAME")
    private String name;
    @Column(name = "PERSON_MISSION_DESTINATION")
    private String destination;
    @Lob
    @Column(name = "PERSON_MISSION_DETAIL")
    private String detail;
    @Column(name = "PERSON_MISSION_MONTH")
    private String month;
    @Column(name = "PERSON_MISSION_ADRESS")
    private String address;

}
