package com.youngsquad.user.domain.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String email;
    private String sex;
    private String nickName;
    private LocalDate birth;
    private String image;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private LocalDateTime createDate;

    public void updateUserProfile(LocalDate birth, String sex) {
        // 유저 정보 업데이트 로직
        this.birth = birth;
        this.sex = sex;
    }

}

