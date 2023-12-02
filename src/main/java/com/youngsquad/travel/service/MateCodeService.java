package com.youngsquad.travel.service;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.mission.domain.TeamMission.TeamMissionMember;
import com.youngsquad.mission.domain.TeamMission.TeamMissionMemberRepo;
import com.youngsquad.travel.domain.MateCode;
import com.youngsquad.travel.domain.MateCodeRepo;
import com.youngsquad.travel.domain.TravelDetail;
import com.youngsquad.travel.domain.TravelDetailRepo;
import com.youngsquad.travel.dto.ViewMateCode;
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
    private final TeamMissionMemberRepo teamMissionMemberRepo;
    private final TravelDetailRepo travelDetailRepo;

    public ViewMateCode viewMateCode(long travelId){
        MateCode mateCode = mateCodeRepo.findFirstByTravelIdOrderByExpireDateTimeDesc(travelId).orElse(null);
        if(mateCode==null) return ViewMateCode.builder().build();
        return ViewMateCode.from(mateCode);
    }

    @Transactional
    public void registerTeamToTravel(long uid, String code){
        MateCode newMateCode = mateCodeRepo.findFirstByCodeOrderByExpireDateTimeDesc(code);
        if( newMateCode==null || isMateCodeExpire(newMateCode)) throw new BusinessException(ErrorCode.NOT_FOUND);
        else registerTeamMate(uid, newMateCode);
    }

    public boolean isMateCodeExpire(MateCode mateCode){
        if(LocalDateTime.now().isAfter(mateCode.getExpireDateTime())) return true;
        else return false;
    }

    public void registerTeamMate(long memberId, MateCode mateCode){
        TravelDetail findTravelDetail = travelDetailRepo.findById(mateCode.getTravelId()).orElse(null);
        if(findTravelDetail == null) throw new BusinessException(ErrorCode.TRAVEL_NOT_FOUND);
        saveTeamMissionMember(findTravelDetail, memberId);
    }

    public void saveTeamMissionMember(TravelDetail travelDetail, long memberId) {
        TeamMissionMember teamMissionMember = TeamMissionMember.to(memberId, travelDetail);
        TeamMissionMember savedMember = teamMissionMemberRepo.save(teamMissionMember);
    }

    public void makeNewMateCode(TravelDetail travelDetail){
        MateCode mateCode = MateCode.builder()
                .expireDateTime(ofExpireDateTime())
                .code(generateRandomCode(6))
                .travelId(travelDetail.getId())
                .build();
        mateCodeRepo.save(mateCode);
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
