package com.youngsquad.record.domain;

import com.youngsquad.mission.domain.Mission;
import com.youngsquad.user.domain.User;
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
@Table(name = "RECORD")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECORD_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="recorder_id")
    private User recorder;
    @ManyToOne
    @JoinColumn(name = "MISSION_ID")
    private Mission mission;
    @Column(name = "IMAGE")
    private String image;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
    @Column(name = "MODIFY_DATE")
    private LocalDateTime modifyDate;
    @Column(name = "DELETE_DATE")
    private LocalDateTime deleteDate;
}
