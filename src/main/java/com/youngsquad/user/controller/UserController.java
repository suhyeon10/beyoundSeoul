package com.youngsquad.user.controller;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import com.youngsquad.user.dto.LoginRequest;
import com.youngsquad.user.dto.UserCreateRequest;
import com.youngsquad.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @ApiOperation(
            value = "회원 정보 생성",
            notes = "별명, 이메일, ~ 을 입력해서 회원 데이터 생성"
    )    @PostMapping(path = "/create")
    public CommonResult createUser(@RequestBody UserCreateRequest userCreateRequest) {
        userService.createUser(userCreateRequest);
        return success();
    }
}
