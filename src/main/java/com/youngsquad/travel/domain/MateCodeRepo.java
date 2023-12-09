package com.youngsquad.travel.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MateCodeRepo extends JpaRepository<MateCode, Long> {

    MateCode findFirstByCodeOrderByExpireDateTimeDesc(String code);
    Optional<MateCode> findFirstByTravelIdOrderByExpireDateTimeDesc(long travelId);
}
