package com.youngsquad.user;


import com.youngsquad.common.s3.S3Service;
import com.youngsquad.user.domain.model.SocialLogin;
import com.youngsquad.user.domain.model.User;
import com.youngsquad.user.domain.model.UserStatus;
import com.youngsquad.user.domain.service.SocialLoginRepository;
import com.youngsquad.user.domain.service.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;


@DataJpaTest
public class SocialLoginServiceTest {

    @Autowired
    private SocialLoginRepository socialLoginRepo;
    @Autowired
    private UserRepository userRepo;
    @MockBean
    private S3Service s3Service;

    /*
    * 로그인
    * 이메일 정보가 존재하면 로그인
    * 이메일 정보가 없으면 회원가입
    * */
    @Test
    @DisplayName("이메일 정보 있는지 확인")
    @Transactional
    public void login(){
        String email = "이메일정보";
        String nickname = "df";
        String imageRoute ="dd";

        String idToken = "dd";
        String sns = "dd";

        User user = userRepo.findByEmail(email).orElse(null);
        if(user == null) user = createUser(email, nickname, imageRoute, idToken, sns);

    }

    /*
    * 회원가입
    * */
    @Test
    public User createUser(String email, String nickName, String imageRoute, String idToken, String sns){
        Long userId = 1L;
        UserStatus userStatus = UserStatus.CERTIFICATION;
        Long socialLoginId = 1L;

        User user = User.builder()
                .id(userId)
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
                .id(socialLoginId)
                .idToken(idToken)
                .sns(sns)
                .user(user)
                .build();
        socialLoginRepo.save(socialLogin);
        Assertions.assertEquals(user.getEmail(), "email");
        return user;
    }

    /*
    프로필 이미지 S3 업로드
     */
    @Test
    public void uploadProfileImage() throws IOException {
        MultipartFile imageFile = createMockMultipartFile();
        String s3Key = s3Service.upload(imageFile, "profile");

        Assertions.assertEquals(s3Key, "");
    }

    public static MultipartFile createMockMultipartFile() throws IOException {
        // 파일을 생성하고 해당 파일을 이용하여 MockMultipartFile을 생성합니다.
        Path path = Paths.get("/static/img/cat.jpg");
        byte[] content = Files.readAllBytes(path);

        // MockMultipartFile을 생성할 때는 파일 이름, 파라미터 이름, 컨텐츠 타입, 파일 내용을 지정합니다.
        return new MockMultipartFile("cat", "cat.jpg", "image/jpeg", content);
    }
}
