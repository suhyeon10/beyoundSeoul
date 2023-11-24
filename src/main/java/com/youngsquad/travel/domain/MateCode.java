package com.youngsquad.travel.domain;

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
@Table(name = "MATE_CODE")
public class MateCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MATE_CODE_ID")
    private Long id;
    @Column(name="TRAVEL_ID")
    private Long travelId;
    @Column(name="MATE_CODE")
    private String mateCode;
    @Column(name="EXPIRE_DATE_TIME")
    private LocalDateTime expireDateTime;
}
