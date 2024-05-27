package com.youngsquad.user.application;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.Response.ResponseService;
import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.common.s3.S3Service;
import com.youngsquad.user.domain.model.User;
import com.youngsquad.user.domain.service.UserRepository;
import com.youngsquad.user.presentation.request.UserCreateRequest;
import com.youngsquad.user.presentation.request.UserModifyRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final ResponseService responseService;
    private final UserRepository userRepository;
    private final S3Service s3Service;
    private static final String DEFAULT_IMAGE_ROUTE = "basic/profile_basic.png";

    @Transactional
    public CommonResult modifyUser(long uid, String sex, String nickname, LocalDate birth, MultipartFile image) throws IOException {
        User user = findUser(uid);
        user.updateUserProfile(birth, sex, nickname, modifyImage(user, image));
        userRepository.save(user);
        return responseService.getSuccessResult();
    }

    public User findUser(long uid){
        return userRepository.findById(uid).orElseThrow(()->new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    public void updateUser(User user, LocalDate parsedBirth, String parsedSex){
        user.updateUserProfile(parsedBirth, parsedSex);
        userRepository.save(user);
    }

    // 신규 가입시 이미지 업로드
    public String uploadProfileImage(MultipartFile image) throws IOException{
        return (image != null) ? s3Service.upload(image, "profile/") : getDefaultImageRoute();
    }

    private String getDefaultImageRoute() {
        return DEFAULT_IMAGE_ROUTE;
    }

    // 회원 수정시 이미지 업로드
    private String modifyImage(User user, MultipartFile image) throws IOException {
        if(image!=null){
            s3Service.deleteImage(user.getImage());
            return s3Service.upload(image, "profile/");
        }else{
            return user.getImage();
        }

    }

}
