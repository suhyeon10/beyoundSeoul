package com.youngsquad.user.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "SEX")
    private String sex;
    @Column(name = "NICKNAME")
    private String nickName;
    @Column(name = "LANG")
    private String lang;
    @Column(name = "AGE")
    private String age;
    @Column(name = "IMAGE")
    private String image;

}

