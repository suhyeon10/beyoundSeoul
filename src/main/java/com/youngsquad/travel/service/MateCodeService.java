package com.youngsquad.travel.service;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.travel.domain.*;

import com.youngsquad.travel.dto.ViewMateCode;
import com.youngsquad.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class MateCodeService {

    private final MateCodeRepo mateCodeRepo;
    private final TravelRepo travelRepo;
    private final TravelParticipateRepo travelParticipateRepo;

    public ViewMateCode getNewMateCode(long travelId){
        Travel findTravelDetail = travelRepo.findById(travelId).orElse(null);
        if(findTravelDetail==null) throw new BusinessException(ErrorCode.TRAVEL_NOT_FOUND);
        return ViewMateCode.from(makeNewMateCode(findTravelDetail));
    }

    public ViewMateCode viewMateCode(long travelId){
        TravelMateCode travelMateCode = mateCodeRepo.findFirstByTravelIdOrderByExpireDateTimeDesc(travelId).orElse(null);
        if(travelMateCode ==null) return ViewMateCode.builder().build();
        return ViewMateCode.from(travelMateCode);
    }

    @Transactional
    public void registerTeamToTravel(User user, String code){
        TravelMateCode newTravelMateCode = mateCodeRepo.findFirstByCodeOrderByExpireDateTimeDesc(code);
        if( newTravelMateCode ==null || isMateCodeExpire(newTravelMateCode)) throw new BusinessException(ErrorCode.NOT_FOUND);
        else registerTeamMate(user, newTravelMateCode);
    }

    public boolean isMateCodeExpire(TravelMateCode travelMateCode){
        if(LocalDateTime.now().isAfter(travelMateCode.getExpireTime())) return true;
        else return false;
    }

    public void registerTeamMate(User user, TravelMateCode travelMateCode){
        Travel findTravelDetail = travelRepo.findById(travelMateCode.getTravel().getId()).orElse(null);
        if(findTravelDetail == null) throw new BusinessException(ErrorCode.TRAVEL_NOT_FOUND);
        saveTeamMissionMember(findTravelDetail, user);
    }

    public void saveTeamMissionMember(Travel travelDetail, User user) {
        TravelParticipate travelParticipate = TravelParticipate.from(user, travelDetail, TeamMemberRole.MEMBER);
        travelParticipateRepo.save(travelParticipate);
    }

    public TravelMateCode makeNewMateCode(Travel travelDetail){
        TravelMateCode travelMateCode = TravelMateCode.builder()
                .expireTime(ofExpireDateTime())
                .mateCode(generateRandomCode(6))
                .travel(travelDetail)
                .build();
        mateCodeRepo.save(travelMateCode);
        return travelMateCode;
    }

    private LocalDateTime ofExpireDateTime(){
        return LocalDateTime.now().plusMinutes(30);
    }

    private static String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomCode = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomCode.append(characters.charAt(index));
        }

        return randomCode.toString();
    }
}
