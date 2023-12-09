package com.youngsquad.user.domain;

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
@Table(name = "USER_DEVICE")
public class UserDevice {
    @Id
    @Column(name = "UUID")
    private Long uuid;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;
}
