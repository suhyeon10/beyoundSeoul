package com.youngsquad.travel.service;

import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.TravelRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service

@RequiredArgsConstructor
public class TravelService {
    private final TravelRepo travelRepo;
    public Travel findTravel(long travelId){
        return travelRepo.findById(travelId).orElse(null);

    }
}
