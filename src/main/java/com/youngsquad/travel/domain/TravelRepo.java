package com.youngsquad.travel.domain;

import com.youngsquad.travel.domain.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelRepo extends JpaRepository<Travel, Long> {
    Optional<Travel> getTopById(long travelId);
}
