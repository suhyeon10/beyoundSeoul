package com.youngsquad.travel.application.matecode;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;

import com.youngsquad.travel.application.TravelService;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.TravelMateCode;
import com.youngsquad.travel.presentation.response.ViewMateCodeResponse;
import com.youngsquad.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class MateCodeService {

    private final TravelService travelService;
    private final TravelMateCodeService travelMateCodeService;
    private final TravelParticipateService travelParticipateService;

    public ViewMateCodeResponse getNewMateCode(long travelId){
        Travel findTravelDetail = travelService.findTravel(travelId);
        if(findTravelDetail==null) throw new BusinessException(ErrorCode.TRAVEL_NOT_FOUND);
        return ViewMateCodeResponse.from(makeNewMateCode(findTravelDetail));
    }

    public ViewMateCodeResponse viewMateCode(long travelId){
        TravelMateCode travelMateCode = travelMateCodeService.findLatestTravelMateCode(travelId);
        if(travelMateCode ==null) return ViewMateCodeResponse.builder().build();
        return ViewMateCodeResponse.from(travelMateCode);
    }

    @Transactional
    public void registerTeamToTravel(User user, String code){
        TravelMateCode newTravelMateCode = travelMateCodeService.findLatestTravelMateCodeByCode(code);
        if( newTravelMateCode ==null || isMateCodeExpire(newTravelMateCode)) throw new BusinessException(ErrorCode.MATE_CODE_NOT_VALUATED);
        else registerTeamMate(user, newTravelMateCode);
    }

    public boolean isMateCodeExpire(TravelMateCode travelMateCode){
        if(LocalDateTime.now().isAfter(travelMateCode.getExpireTime())) return true;
        else return false;
    }

    public void registerTeamMate(User user, TravelMateCode travelMateCode){
        Travel findTravelDetail = travelService.findTravel(travelMateCode.getTravel().getId());
        if(findTravelDetail == null) throw new BusinessException(ErrorCode.TRAVEL_NOT_FOUND);
        travelParticipateService.saveTeamMissionMember(findTravelDetail, user);
    }



    public TravelMateCode makeNewMateCode(Travel travel){
        TravelMateCode travelMateCode = TravelMateCode.from(travel);
        travelMateCodeService.save(travelMateCode);
        return travelMateCode;
    }



}
