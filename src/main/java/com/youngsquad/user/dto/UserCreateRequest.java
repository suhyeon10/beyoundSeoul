package com.youngsquad.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserCreateRequest {
    private String nickName;
    private String sex;
    private String email;
    private String lang;
}
