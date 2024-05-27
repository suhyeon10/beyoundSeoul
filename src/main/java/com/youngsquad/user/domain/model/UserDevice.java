package com.youngsquad.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_device")
public class UserDevice {
    @Id
    @Column(name = "uuid")
    private Long uuid;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
