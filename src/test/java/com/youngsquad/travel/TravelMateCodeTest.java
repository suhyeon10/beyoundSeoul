//package com.youngsquad.travel;
//
//import com.youngsquad.common.exception.BusinessException;
//import com.youngsquad.common.exception.ErrorCode;
//import com.youngsquad.mission.domain.TeamMission.TeamMissionMember;
//import com.youngsquad.mission.domain.TeamMission.TeamMissionMemberRepo;
//import com.youngsquad.travel.domain.model.TravelMateCode;
//import com.youngsquad.travel.domain.MateCodeRepo;
//import com.youngsquad.travel.domain.TravelDetail;
//import com.youngsquad.travel.domain.TravelDetailRepo;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//
//import java.time.LocalDateTime;
//import java.util.Random;
//
//@DataJpaTest
//public class TravelMateCodeTest {
//    @Autowired
//    private TeamMissionMemberRepo teamMissionMemberRepo;
//    @Autowired
//    private TravelDetailRepo travelDetailRepo;
//    @Autowired
//    private MateCodeRepo mateCodeRepo;
//    @Test
//    public void test(){
//        String code = "냥";
//        long uid = 0L;
//
//        // 여행 메이트코드 조회
//
//
//
//        // 여행 메이트로 등록
//        registerTeamToTravel(uid, code);
//
//    }
//
//    @Test
//    @DisplayName("메이트코드 조회 테스트")
//    public void getMateCode(){
//        long travelId = 0L;
//        TravelMateCode travelMateCode = mateCodeRepo.findFirstByTravelIdOrderByExpireDateTimeDesc(travelId).orElse(null);
//
//        if(travelMateCode ==null) ;
//
//
//
//    }
//
//
//    @Test
//    @DisplayName("메이트코드를 통해 팀원을 여행에 합류 테스트")
//    public void registerTeamToTravel(long uid, String code){
//        // 여행코드 만료되었거나 없는 여행코드라면 팀원 등록 실패 throw
//
////        MateCode expiredMateCode = MateCode.builder()
////                .expireDateTime(LocalDateTime.now().minusDays(1))
////                .code(code)
////                .travelId(1L)
////                .build();
////        TravelDetail travelDetail = TravelDetail.builder().id(1L).build();
////        travelDetailRepo.save(travelDetail);
////
////        //여행코드가 만료가 아니면 팀원 등록 성공
////        MateCode successMateCode = MateCode.builder()
////                .expireDateTime(LocalDateTime.now().plusDays(1))
////                .code(code)
////                .travelId(1L)
////                .build();
////        mateCodeRepo.save(expiredMateCode);
//
//        TravelMateCode newTravelMateCode = mateCodeRepo.findFirstByCodeOrderByExpireDateTimeDesc(code);
//
//        if( newTravelMateCode ==null || isMateCodeExpire(newTravelMateCode)) throw new BusinessException(ErrorCode.NOT_FOUND);
//        else registerTeamMate(uid, newTravelMateCode);
//    }
//
//    @Test
//    public void viewTravelMateCode(){
//        long travelId =0L;
//        TravelMateCode travelMateCode1 = getMateCode(travelId);
//    }
//
//    public TravelMateCode getMateCode(long travelId){
//        return TravelMateCode.builder().travelId(travelId).build();
//    }
//
//    @Test
//    @DisplayName("메이트 코드 save")
//    public void makeNewMateCode(){
//        long travelId = 0L;
//        TravelMateCode travelMateCode = TravelMateCode.builder()
//                .expireDateTime(ofExpireDateTime())
//                .code(generateRandomCode(6))
//                .travelId(travelId)
//                .build();
//        mateCodeRepo.save(travelMateCode);
//    }
//
//    @DisplayName("랜덤 메이트 코드 작성")
//    public static String generateRandomCode(int length) {
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        StringBuilder randomCode = new StringBuilder();
//
//        Random random = new Random();
//        for (int i = 0; i < length; i++) {
//            int index = random.nextInt(characters.length());
//            randomCode.append(characters.charAt(index));
//        }
//
//        return randomCode.toString();
//    }
//
//
//    @Test
//    @DisplayName("여행 메이트 코드 save 테스트")
//    public void registerTeamMate(long memberId, TravelMateCode travelMateCode){
//
//        TravelDetail findTravelDetail = travelDetailRepo.findById(travelMateCode.getTravelId()).orElse(null);
//        if(findTravelDetail == null) throw new BusinessException(ErrorCode.TRAVEL_NOT_FOUND);
//        saveTeamMissionMember(findTravelDetail, memberId);
//        //save 하는 로직
//    }
//
//
//
//    @Test
//    @DisplayName("여행 팀 멤버가 DB에 저장이 잘 되는지 확인")
//    public void saveTeamMissionMember(TravelDetail travelDetail, long memberId) {
//        // given
//        TravelDetail savedTravel = travelDetailRepo.save(travelDetail);
//        TeamMissionMember teamMissionMember = TeamMissionMember.builder()
//                .memberId(memberId)
//                .travelDetail(savedTravel)
//                .build();
//        // when
//        TeamMissionMember savedMember = teamMissionMemberRepo.save(teamMissionMember);
//        // then
//        Assertions.assertThat(teamMissionMember).isSameAs(savedMember);
//        Assertions.assertThat(teamMissionMember.getTravelDetail()).isEqualTo(savedMember.getTravelDetail());
//        Assertions.assertThat(savedMember.getId()).isNotNull();
//        Assertions.assertThat(teamMissionMemberRepo.count()).isEqualTo(1);
//    }
//
//    @DisplayName("여행 메이트 코드 만료 확인")
//    public boolean isMateCodeExpire(TravelMateCode travelMateCode){
//        if(LocalDateTime.now().isAfter(travelMateCode.getExpireDateTime())) return true;
//        else return false;
//    }
//
//    public LocalDateTime ofExpireDateTime(){
//        return LocalDateTime.now().plusMinutes(30);
//    }
//
//
//}
