package com.youngsquad.travel.application.home;

import com.youngsquad.common.s3.S3Service;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.presentation.response.HomeResponse;
import com.youngsquad.user.domain.model.User;
import com.youngsquad.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeService {

    private final UserService userService;
    private final S3Service s3Service;
    private final HomeTravelReadService homeTravelReadService;

    public HomeResponse viewHome(long uid){
        // 0) 유저 프로필 정보 (항상)
        User user = userService.findUser(uid);
        Travel travel = homeTravelReadService.getUserTravel(user);

        // 1) 미션 진행 현황

        // 2) 미션 정보

        return HomeResponse.builder()
                .travelResponse(homeTravelReadService.makeResponse(travel))
                .build();
    }

    public HomeResponse.Profile ofProfile(User user){
        return HomeResponse.Profile.builder()
                .userName(user.getNickName())
                .userImage(s3Service.getDownloadPresignedURL(user.getImage()))
                .build();
    }
}
