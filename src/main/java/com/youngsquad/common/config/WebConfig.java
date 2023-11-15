package com.youngsquad.common.config;

import com.youngsquad.common.log.LogInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LogInterceptor logInterceptor;
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins(
//                        "https://aidelete.net",
//                        "http://192.168.1.68:3000",
//                        "http://192.168.1.113:3000",
//                        "http://localhost:3000",
//                        "https://api.medicalip.biz",
//                        "https://suhyeonjang1010-bucket.s3.ap-northeast-2.amazonaws.com",
//                        "https://medicalip-bucket.s3.ap-northeast-2.amazonaws.com"
//                )
//                .allowedMethods("*")
//                .allowedHeaders("*")
//                .maxAge(3000)
//        ;
//    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/**")
                .addResourceLocations("classpath:/static/img");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/**");
    }

}

