package com.youngsquad.user.presentation;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import com.youngsquad.user.presentation.request.UserCreateRequest;
import com.youngsquad.user.application.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
