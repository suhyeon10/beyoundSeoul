package com.youngsquad.travel.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MateCodeRepo extends JpaRepository<TravelMateCode, Long> {

    TravelMateCode findFirstByCodeOrderByExpireDateTimeDesc(String code);
    Optional<TravelMateCode> findFirstByTravelIdOrderByExpireDateTimeDesc(long travelId);
}
