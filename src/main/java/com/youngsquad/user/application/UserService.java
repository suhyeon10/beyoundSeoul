package com.youngsquad.user.application;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.Response.ResponseService;
import com.youngsquad.user.domain.model.User;
import com.youngsquad.user.domain.service.UserRepository;
import com.youngsquad.user.presentation.request.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final ResponseService responseService;
    private final UserRepository userRepo;
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
