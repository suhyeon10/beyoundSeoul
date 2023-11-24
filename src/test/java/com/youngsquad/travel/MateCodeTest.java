package com.youngsquad.travel;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.travel.domain.MateCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class MateCodeTest {

    @Test
    public void 여행코드_등록(){
        // 여행코드 만료되었거나 없는 여행코드라면 등록 실패 throw
        MateCode expiredMateCode = MateCode.builder()
                .expireDateTime(LocalDateTime.now().minusDays(1)).build();
        assertThrows(BusinessException.class, () -> isMateCodeExpire(expiredMateCode));

        //여행코드가 만료가 아니면 등록 성공
        MateCode successMateCode = MateCode.builder()
                .expireDateTime(LocalDateTime.now().plusDays(1)).build();
        assertEquals(true, isMateCodeExpire(successMateCode));


        MateCode mateCode = MateCode.builder().build();
        if( mateCode==null || isMateCodeExpire(mateCode)) throw new RuntimeException();
        else registerMateCode();
    }

    @Test
    public void 여행코드_조회(){

    }

    @Test
    public void 여행코드_생성(){

    }
    public void registerMateCode(){
        //메이트코드를 통해 팀원을 여행에 합류
    }


    public MateCode createAndSaveMateCode(long travelId){
        // 메이트코드 생성후 저장하는 로직
        return MateCode.builder().build();
    }
    public boolean isMateCodeExpire(MateCode mateCode){
        if(LocalDateTime.now().isAfter(mateCode.getExpireDateTime())) throw new BusinessException(ErrorCode.NOT_FOUND);
        else return true;
    }
    @Test
    public LocalDateTime ofExpireDateTime(){
        return LocalDateTime.now().plusMinutes(30);
    }


}
