package com.youngsquad.user.service;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.Response.ResponseService;
import com.youngsquad.user.dto.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final ResponseService responseService;
    public CommonResult createUser(UserCreateRequest userCreateRequest) {

        return responseService.getSuccessResult();
    }

    public CommonResult login(UserCreateRequest userCreateRequest) {

        return responseService.getSuccessResult();
    }
}
