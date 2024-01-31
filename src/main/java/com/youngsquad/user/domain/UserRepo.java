package com.youngsquad.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    User findById(long id);
    Optional<User> findByEmail(String email);
}
