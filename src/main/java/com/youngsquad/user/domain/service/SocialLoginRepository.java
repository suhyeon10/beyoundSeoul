package com.youngsquad.user.domain.service;

import com.youngsquad.user.domain.model.SocialLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialLoginRepository extends JpaRepository<SocialLogin, Long> {
    Optional<SocialLogin> findByIdToken(String idToken);
}
