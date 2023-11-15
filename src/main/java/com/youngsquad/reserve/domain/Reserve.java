package com.youngsquad.reserve.domain;

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
@Table(name = "RESERVE")
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVE_ID")
    private Long id;
    @Column(name = "DEPART")
    private String depart;
    @Column(name = "ARRIVE")
    private String arrive;
    @Column(name = "DEPART_DATE")
    private LocalDateTime departDate;
    @Column(name = "DETAIL")
    private String detail;
    @Column(name = "TOTAL_NUM")
    private String totalNum;
    @Column(name = "RESERVE_NUM")
    private String reserveNum;
    @Column(name = "RESERVE_TYPE")
    private String reserveType;
}
