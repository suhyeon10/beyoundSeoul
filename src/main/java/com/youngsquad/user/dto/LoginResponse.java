package com.youngsquad.user.dto;

import com.youngsquad.user.domain.User;
import com.youngsquad.user.domain.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String email;
    private String sex;
    private String nickName;
    private LocalDate birth;
    private String image;
    private UserStatus status;
    private LocalDateTime createDate;
    private String registerYN;

    public static LoginResponse makeResponse(User user, String registerYN){
        return LoginResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .sex(user.getSex())
                .nickName(user.getNickName())
                .birth(user.getBirth())
                .image(user.getImage())
                .status(user.getStatus())
                .createDate(user.getCreateDate())
                .registerYN(registerYN)
                .build();
    }

}
