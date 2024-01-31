package com.youngsquad.travel.application;

import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.service.TravelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service

@RequiredArgsConstructor
public class TravelService {
    private final TravelRepository travelRepository;
    public Travel findTravel(long travelId){
        return travelRepository.findById(travelId).orElse(null);

    }
}
