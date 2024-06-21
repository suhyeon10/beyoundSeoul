package com.youngsquad.user.application;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.common.s3.S3Service;
import com.youngsquad.travel.domain.model.TravelParticipate;
import com.youngsquad.travel.domain.service.TravelParticipateRepository;
import com.youngsquad.travel.domain.service.TravelRepository;
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
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocialLoginService {
    private final UserRepository userRepository;
    private final SocialLoginRepository socialLoginRepository;
    private final TravelParticipateRepository travelParticipateRepository;
    private final UserService userService;
    private final S3Service s3Service;



    @Transactional
    public LoginResponse login(String email, String nickName, MultipartFile image, String idToken, String sns) throws IOException {
        validateInput(email, idToken, sns);

        User user = userRepository.findByEmail(email).orElse(null);
        
        if (user == null || !isSocialLoginExists(idToken)) {
            user = registerNewUser(email, nickName, image, idToken, sns);
        }

        String registerYN = checkOnboardingStatus(user);
        log.info("registerYN :: "+registerYN);
        String imageURL = s3Service.getDownloadPresignedURL(user.getImage());
        return LoginResponse.makeResponse(user, imageURL, registerYN);
    }

    public String checkOnboardingStatus(User user) {
        List<TravelParticipate> travelParticipates = travelParticipateRepository.findByTeamMemberOrderByCreateDateDesc(user);

        if (travelParticipates.size() == 0) {
            return "N";
        }
        return "Y";
    }


    private void validateInput(String email, String idToken, String sns) {
        if (email == null || idToken == null || sns == null) {
            throw new BusinessException(ErrorCode.USER_ID_TOKEN_NOT_FOUND);
        }
    }

    private boolean isSocialLoginExists(String idToken) {
        return socialLoginRepository.findByIdToken(idToken).isPresent();
    }

    private User registerNewUser(String email, String nickName, MultipartFile image, String idToken, String sns) throws IOException {
        User user = createUser(email, nickName, image);
        createSocialLogin(user, idToken, sns);
        return user;
    }

    private User createUser(String email, String nickName, MultipartFile image) throws IOException {
        String imageRoute = userService.uploadProfileImage(image);

        User user = User.builder()
                .email(email)
                .nickName(nickName)
                .image(imageRoute)
                .status(UserStatus.CERTIFICATION)
                .sex("")
                .birth(null)
                .createDate(LocalDateTime.now())
                .build();
        userRepository.save(user);
        return user;
    }

    private void createSocialLogin(User user, String idToken, String sns) {
        SocialLogin socialLogin = SocialLogin.builder()
                .idToken(idToken)
                .sns(sns)
                .user(user)
                .build();
        socialLoginRepository.save(socialLogin);
    }

}
