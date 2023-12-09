package com.youngsquad.travel;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.mission.domain.TeamMission.TeamMissionMember;
import com.youngsquad.mission.domain.TeamMission.TeamMissionMemberRepo;
import com.youngsquad.travel.domain.MateCode;
import com.youngsquad.travel.domain.MateCodeRepo;
import com.youngsquad.travel.domain.TravelDetail;
import com.youngsquad.travel.domain.TravelDetailRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class MateCodeTest {
    @Autowired
    private TeamMissionMemberRepo teamMissionMemberRepo;
    @Autowired
    private TravelDetailRepo travelDetailRepo;
    @Autowired
    private MateCodeRepo mateCodeRepo;
    @Test
    public void test(){
        String code = "냥";
        long uid = 0L;

        // 여행 메이트코드 조회



        // 여행 메이트로 등록
        registerTeamToTravel(uid, code);

    }

    @Test
    @DisplayName("메이트코드 조회 테스트")
    public void getMateCode(){
        long travelId = 0L;
        MateCode mateCode = mateCodeRepo.findFirstByTravelIdOrderByExpireDateTimeDesc(travelId).orElse(null);

        if(mateCode==null) ;



    }


    @Test
    @DisplayName("메이트코드를 통해 팀원을 여행에 합류 테스트")
    public void registerTeamToTravel(long uid, String code){
        // 여행코드 만료되었거나 없는 여행코드라면 팀원 등록 실패 throw

//        MateCode expiredMateCode = MateCode.builder()
//                .expireDateTime(LocalDateTime.now().minusDays(1))
//                .code(code)
//                .travelId(1L)
//                .build();
//        TravelDetail travelDetail = TravelDetail.builder().id(1L).build();
//        travelDetailRepo.save(travelDetail);
//
//        //여행코드가 만료가 아니면 팀원 등록 성공
//        MateCode successMateCode = MateCode.builder()
//                .expireDateTime(LocalDateTime.now().plusDays(1))
//                .code(code)
//                .travelId(1L)
//                .build();
//        mateCodeRepo.save(expiredMateCode);

        MateCode newMateCode = mateCodeRepo.findFirstByCodeOrderByExpireDateTimeDesc(code);

        if( newMateCode==null || isMateCodeExpire(newMateCode)) throw new BusinessException(ErrorCode.NOT_FOUND);
        else registerTeamMate(uid, newMateCode);
    }

    @Test
    public void viewTravelMateCode(){
        long travelId =0L;
        MateCode mateCode1 = getMateCode(travelId);
    }

    public MateCode getMateCode(long travelId){
        return MateCode.builder().travelId(travelId).build();
    }

    @Test
    @DisplayName("메이트 코드 save")
    public void makeNewMateCode(){
        long travelId = 0L;
        MateCode mateCode = MateCode.builder()
                .expireDateTime(ofExpireDateTime())
                .code(generateRandomCode(6))
                .travelId(travelId)
                .build();
        mateCodeRepo.save(mateCode);
    }

    @DisplayName("랜덤 메이트 코드 작성")
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


    @Test
    @DisplayName("여행 메이트 코드 save 테스트")
    public void registerTeamMate(long memberId, MateCode mateCode){

        TravelDetail findTravelDetail = travelDetailRepo.findById(mateCode.getTravelId()).orElse(null);
        if(findTravelDetail == null) throw new BusinessException(ErrorCode.TRAVEL_NOT_FOUND);
        saveTeamMissionMember(findTravelDetail, memberId);
        //save 하는 로직
    }



    @Test
    @DisplayName("여행 팀 멤버가 DB에 저장이 잘 되는지 확인")
    public void saveTeamMissionMember(TravelDetail travelDetail, long memberId) {
        // given
        TravelDetail savedTravel = travelDetailRepo.save(travelDetail);
        TeamMissionMember teamMissionMember = TeamMissionMember.builder()
                .memberId(memberId)
                .travelDetail(savedTravel)
                .build();
        // when
        TeamMissionMember savedMember = teamMissionMemberRepo.save(teamMissionMember);
        // then
        Assertions.assertThat(teamMissionMember).isSameAs(savedMember);
        Assertions.assertThat(teamMissionMember.getTravelDetail()).isEqualTo(savedMember.getTravelDetail());
        Assertions.assertThat(savedMember.getId()).isNotNull();
        Assertions.assertThat(teamMissionMemberRepo.count()).isEqualTo(1);
    }

    @DisplayName("여행 메이트 코드 만료 확인")
    public boolean isMateCodeExpire(MateCode mateCode){
        if(LocalDateTime.now().isAfter(mateCode.getExpireDateTime())) return true;
        else return false;
    }

    public LocalDateTime ofExpireDateTime(){
        return LocalDateTime.now().plusMinutes(30);
    }


}
