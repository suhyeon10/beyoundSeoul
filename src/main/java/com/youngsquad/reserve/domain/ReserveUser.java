package com.youngsquad.reserve.domain;

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
@Table(name = "RESERVE_USER")
public class ReserveUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVE_USER_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="RESERVE_ID")
    private Reserve reserve;
    @Column(name = "RESERVE_NUM")
    private Integer reserveNum;
    @Column(name = "USER_ID")
    private long userId;
}
