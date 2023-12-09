package com.youngsquad.home;

import com.youngsquad.common.s3.S3Service;
import com.youngsquad.home.dto.HomeRes;
import com.youngsquad.user.domain.User;
import com.youngsquad.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeService {

    private final UserService userService;
    private final S3Service s3Service;


    public HomeRes viewHome(long uid){

        // 0) 유저 프로필 정보 (항상)
        User user = userService.findUser(uid);

        return null;
    }

    public HomeRes.Profile ofProfile(User user){
        return HomeRes.Profile.builder()
                .userName(user.getNickName())
                .userImage(s3Service.getDownloadPresignedURL(user.getImage()))
                .build();
    }






}
