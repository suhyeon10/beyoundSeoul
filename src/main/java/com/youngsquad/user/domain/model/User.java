package com.youngsquad.user.domain.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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


    public void updateUserProfile(LocalDate birth, String sex, String nickname, String image){
        this.birth = (birth!=null) ? birth : this.birth;
        this.sex = (sex!=null) ? sex : this.sex;
        this.nickName = (nickname!=null) ? nickname : this.nickName;
        this.image = (image!=null) ? image : this.image;
    }
    public void updateUserProfile(LocalDate birth, String sex) {
        // 유저 정보 업데이트 로직
        this.birth = (birth!=null) ? birth : this.birth;
        this.sex = (sex!=null) ? sex : this.sex;
    }

}

