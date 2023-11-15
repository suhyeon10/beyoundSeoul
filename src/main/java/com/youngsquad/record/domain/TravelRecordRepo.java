package com.youngsquad.record.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelRecordRepo extends JpaRepository<TravelRecord, Long> {

    List<TravelRecord> getTravelRecordsByTravelIdAndUserId(long travelId, long userId);
}
