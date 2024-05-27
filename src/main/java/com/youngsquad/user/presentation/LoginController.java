package com.youngsquad.user.presentation;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import com.youngsquad.user.presentation.request.LoginRequest;
import com.youngsquad.user.presentation.response.LoginResponse;
import com.youngsquad.user.application.SocialLoginService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
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
        log.info("email : "+loginRequest.email());
        log.info("nickName : "+loginRequest.nickName());
        log.info("image : "+loginRequest.image());
        log.info("idToken : "+loginRequest.idToken());
        log.info("sns : "+loginRequest.sns());
        LoginResponse loginResponse = socialLoginService.login(loginRequest.email(),
                loginRequest.nickName(),
                loginRequest.image(),
                loginRequest.idToken(),
                loginRequest.sns());
        return result(loginResponse);
    }
}
