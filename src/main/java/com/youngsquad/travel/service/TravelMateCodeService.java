package com.youngsquad.travel.service;

import com.youngsquad.travel.domain.model.TravelMateCode;
import com.youngsquad.travel.domain.TravelMateCodeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelMateCodeService {

    private final TravelMateCodeRepo travelMateCodeRepo;
    public TravelMateCode findLatestTravelMateCode(long travelId){
        return travelMateCodeRepo.findFirstByTravelIdOrderByExpireTimeDesc(travelId).orElse(null);
    }
    public TravelMateCode findLatestTravelMateCodeByCode(String code){
        return travelMateCodeRepo.findFirstByMateCodeOrderByExpireTimeDesc(code);
    }
    public void save(TravelMateCode travelMateCode){
        travelMateCodeRepo.save(travelMateCode);
    }
}
