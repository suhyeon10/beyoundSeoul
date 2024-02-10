package com.youngsquad.travel.domain.service;

import com.youngsquad.travel.domain.model.TravelTheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelThemeRepository extends JpaRepository<TravelTheme, Long> {
    Optional<TravelTheme> findById(long themeId);
}
