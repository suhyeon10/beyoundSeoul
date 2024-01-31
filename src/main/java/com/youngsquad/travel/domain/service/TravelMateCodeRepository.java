package com.youngsquad.travel.domain.service;

import com.youngsquad.travel.domain.model.TravelMateCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelMateCodeRepository extends JpaRepository<TravelMateCode, Long> {

    TravelMateCode findFirstByMateCodeOrderByExpireTimeDesc(String code);
    Optional<TravelMateCode> findFirstByTravelIdOrderByExpireTimeDesc(long travelId);
}
