package com.youngsquad.user.service;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.Response.ResponseService;
import com.youngsquad.user.domain.User;
import com.youngsquad.user.domain.UserRepo;
import com.youngsquad.user.dto.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final ResponseService responseService;
    private final UserRepo userRepo;
    public CommonResult createUser(UserCreateRequest userCreateRequest) {

        return responseService.getSuccessResult();
    }

    public CommonResult login(UserCreateRequest userCreateRequest) {

        return responseService.getSuccessResult();
    }

    public User findUser(long uid){
        return userRepo.findById(uid);
    }
}
