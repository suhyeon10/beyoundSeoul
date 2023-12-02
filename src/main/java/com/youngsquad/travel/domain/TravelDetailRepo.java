package com.youngsquad.travel.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelDetailRepo extends JpaRepository<TravelDetail, Long> {
    Optional<TravelDetail> getTopById(long travelId);

    Optional<TravelDetail> findFirstById(long travelId);
}
