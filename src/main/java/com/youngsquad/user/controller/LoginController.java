package com.youngsquad.user.controller;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import com.youngsquad.user.domain.User;
import com.youngsquad.user.dto.LoginRequest;
import com.youngsquad.user.dto.LoginResponse;
import com.youngsquad.user.dto.UserCreateRequest;
import com.youngsquad.user.service.SocialLoginService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class LoginController extends BaseController {

    private final SocialLoginService socialLoginService;

    /**
    로그인
     */
    @ApiOperation(
            value = "로그인",
            notes = "이메일을 입력해서 로그인, 회원 데이터가 없으면 생성"
    )    @PostMapping(path = "/login")
    public CommonResult login( LoginRequest loginRequest) throws IOException {
        LoginResponse loginResponse = socialLoginService.login(loginRequest.email(),
                loginRequest.nickName(),
                loginRequest.image(),
                loginRequest.idToken(),
                loginRequest.sns());
        return result(loginResponse);
    }
}
