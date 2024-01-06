package com.youngsquad.user.dto;


import org.springframework.web.multipart.MultipartFile;

public record LoginRequest (String email,
                            String nickName,
                            MultipartFile image,
                            String idToken,
                            String sns){
}
