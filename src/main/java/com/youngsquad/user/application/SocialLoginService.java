package com.youngsquad.user.application;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.common.s3.S3Service;
import com.youngsquad.user.domain.model.SocialLogin;
import com.youngsquad.user.domain.model.User;
import com.youngsquad.user.domain.model.UserStatus;
import com.youngsquad.user.domain.service.SocialLoginRepository;
import com.youngsquad.user.domain.service.UserRepository;
import com.youngsquad.user.presentation.response.LoginResponse;
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
    private final UserRepository userRepo;
    private final SocialLoginRepository socialLoginRepo;
    private final UserService userService;

    @Transactional
    public LoginResponse login(String email, String nickName, MultipartFile image, String idToken, String sns) throws IOException {
        String registerYN = "N";
        User user = userRepo.findByEmail(email).orElse(null);
        if(user == null) {
            user = createUser(email, nickName, image, idToken, sns);
            registerYN = "Y";
        }
        return LoginResponse.makeResponse(user, registerYN);
    }

    public User createUser(String email, String nickName, MultipartFile image, String idToken, String sns) throws IOException {


        UserStatus userStatus = UserStatus.CERTIFICATION;
        if(idToken.isEmpty() || sns.isEmpty()) throw new BusinessException(ErrorCode.USER_ID_TOKEN_NOT_FOUND);
        String imageRoute = userService.uploadProfileImage(image);

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

}
