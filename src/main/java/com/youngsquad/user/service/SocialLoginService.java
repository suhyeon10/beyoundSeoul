package com.youngsquad.user.service;

import com.youngsquad.common.s3.S3Service;
import com.youngsquad.user.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocialLoginService {
    private final UserRepo userRepo;
    private final SocialLoginRepo socialLoginRepo;
    private final S3Service s3Service;

    private static final String DEFAULT_IMAGE_ROUTE = "basic/profile_basic.png";

    @Transactional
    public User login(String email, String nickName, MultipartFile image, String idToken, String sns) throws IOException {
        User user = userRepo.findByEmail(email).orElse(null);
        if(user == null) user = createUser(email, nickName, image, idToken, sns);
        return user;
    }

    public User createUser(String email, String nickName, MultipartFile image, String idToken, String sns) throws IOException {
        UserStatus userStatus = UserStatus.CERTIFICATION;
        String imageRoute = (image != null) ? uploadProfileImage(image) : getDefaultImageRoute();

        User user = User.builder()
                .email(email)
                .nickName(nickName)
                .image(imageRoute)
                .status(userStatus)
                .sex("")
                .birth(null)
                .createDate(LocalDateTime.now())
                .build();
        userRepo.save(user);

        SocialLogin socialLogin = SocialLogin.builder()
                .idToken(idToken)
                .sns(sns)
                .user(user)
                .build();
        socialLoginRepo.save(socialLogin);
        return user;
    }
    public String uploadProfileImage(MultipartFile profileImage) throws IOException {
        return s3Service.upload(profileImage, "profile/");
    }

    private String getDefaultImageRoute() {
        return DEFAULT_IMAGE_ROUTE;  // 실제로 사용할 기본 이미지 경로로 대체되어야 합니다.
    }

}
