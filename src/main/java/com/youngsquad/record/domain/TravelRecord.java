package com.youngsquad.record.domain;

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
@Table(name = "TRAVEL_RECORD")
public class TravelRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECORD_ID")
    private Long id;
    @Column(name = "USER_ID")
    private long userId;
    @Column(name = "UPLOAD_AT")
    private LocalDateTime uploadAt;
    @Column(name = "TEXT")
    private String text;
    @Column(name = "IMAGE")
    private String image;
    @Column(name = "MISSION_TYPE")
    private String missionType;
    @Column(name = "MISSION_STATUS_ID")
    private long missionStatusId;
    @Column(name = "TRAVEL_ID")
    private long travelId;

}
