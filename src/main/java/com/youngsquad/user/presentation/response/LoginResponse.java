package com.youngsquad.user.presentation.response;

import com.youngsquad.user.domain.model.User;
import com.youngsquad.user.domain.model.UserStatus;
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

    public static LoginResponse makeResponse(User user, String imageURL, String registerYN){
        return LoginResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .sex(user.getSex())
                .nickName(user.getNickName())
                .birth(user.getBirth())
                .image(imageURL)
                .status(user.getStatus())
                .createDate(user.getCreateDate())
                .registerYN(registerYN)
                .build();
    }

}
