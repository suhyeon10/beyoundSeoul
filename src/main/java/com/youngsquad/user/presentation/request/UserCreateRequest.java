package com.youngsquad.user.presentation.request;

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
