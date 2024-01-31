package com.youngsquad.travel.application.matecode;

import com.youngsquad.travel.domain.model.TravelMateCode;
import com.youngsquad.travel.domain.service.TravelMateCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelMateCodeService {

    private final TravelMateCodeRepository travelMateCodeRepository;
    public TravelMateCode findLatestTravelMateCode(long travelId){
        return travelMateCodeRepository.findFirstByTravelIdOrderByExpireTimeDesc(travelId).orElse(null);
    }
    public TravelMateCode findLatestTravelMateCodeByCode(String code){
        return travelMateCodeRepository.findFirstByMateCodeOrderByExpireTimeDesc(code);
    }
    public void save(TravelMateCode travelMateCode){
        travelMateCodeRepository.save(travelMateCode);
    }
}
