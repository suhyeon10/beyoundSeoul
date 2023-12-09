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
@Table(name = "TRAVEL_MATE_CODE")
public class TravelMateCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TRAVEL_MATE_CODE_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="TRAVEL_ID")
    private Travel travel;
    @Column(name="MATE_CODE")
    private String mateCode;
    @Column(name="EXPIRE_DATE_TIME")
    private LocalDateTime expireTime;
}
