package com.youngsquad.user.service;

import com.youngsquad.user.domain.User;
import com.youngsquad.user.domain.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeUserService {
    private final UserRepo userRepo;

    public User getUser(long uid){
        return userRepo.getTopById(uid);

    }
}
