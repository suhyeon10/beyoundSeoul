package com.youngsquad.travel;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.mission.domain.TeamMission.TeamMissionMember;
import com.youngsquad.travel.domain.MateCode;
import com.youngsquad.travel.domain.TravelDetail;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MateCodeTest {

    @Test
    public void 여행코드_팀원등록(){
        // 여행코드 만료되었거나 없는 여행코드라면 팀원 등록 실패 throw
        long userId=0L;
        MateCode expiredMateCode = MateCode.builder()
                .expireDateTime(LocalDateTime.now().minusDays(1)).build();
        assertThrows(BusinessException.class, () -> isMateCodeExpire(expiredMateCode));

        //여행코드가 만료가 아니면 팀원 등록 성공
        MateCode successMateCode = MateCode.builder()
                .expireDateTime(LocalDateTime.now().plusDays(1)).build();
        assertEquals(true, isMateCodeExpire(successMateCode));


        MateCode mateCode = MateCode.builder().build();
        if( mateCode==null || isMateCodeExpire(mateCode)) throw new RuntimeException();
        else registerTeamMate(userId, mateCode);
    }

    @Test
    public void 여행의_메이트_코드_조회(){
        long travelId =0L;
        MateCode mateCode1 = getMateCode(travelId);
    }

    public MateCode getMateCode(long travelId){
        return MateCode.builder().travelId(travelId).build();
    }

    @Test
    public void 여행코드_생성(){
        long travelId = 0L;
        MateCode mateCode = MateCode.builder()
                .expireDateTime(ofExpireDateTime())
                .mateCode(generateRandomCode(6))
                .travelId(travelId)
                .build();
        // 메이트 코드 save
    }

    //랜덤 메이트 코드 작성
    public static String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomCode = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomCode.append(characters.charAt(index));
        }

        return randomCode.toString();
    }
    public void registerTeamMate(long memberId, MateCode mateCode){
        //메이트코드를 통해 팀원을 여행에 합류
        long travelId = mateCode.getTravelId();
        TravelDetail travelDetail = TravelDetail.builder().id(travelId).build();
        TeamMissionMember teamMissionMember = TeamMissionMember.builder()
                .travelDetail(travelDetail)
                .memberId(memberId)
                .build();

    }

    //만료확인
    public boolean isMateCodeExpire(MateCode mateCode){
        if(LocalDateTime.now().isAfter(mateCode.getExpireDateTime())) throw new BusinessException(ErrorCode.NOT_FOUND);
        else return true;
    }

    public LocalDateTime ofExpireDateTime(){
        return LocalDateTime.now().plusMinutes(30);
    }


}
