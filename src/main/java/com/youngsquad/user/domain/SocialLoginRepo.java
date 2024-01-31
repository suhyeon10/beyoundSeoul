package com.youngsquad.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialLoginRepo extends JpaRepository<SocialLogin, Long> {
    Optional<SocialLogin> findByIdToken(String idToken);
}
