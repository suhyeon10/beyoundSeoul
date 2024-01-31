//package com.youngsquad.home.travel;
//
//
//import com.youngsquad.common.exception.BusinessException;
//import com.youngsquad.common.exception.ErrorCode;
//import com.youngsquad.travel.domain.*;
//import com.youngsquad.user.domain.SocialLogin;
//import com.youngsquad.user.domain.SocialLoginRepo;
//import com.youngsquad.user.domain.User;
//import com.youngsquad.user.domain.UserRepo;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//@DataJpaTest
//public class HomeServiceTest {
//
//    @Autowired
//    private UserRepo userRepo;
//    @Autowired
//    private SocialLoginRepo socialLoginRepo;
//    @Autowired
//    private TravelRepo travelRepo;
//    @Autowired
//    private TravelParticipateRepo travelParticipateRepo;
//
//    @DisplayName("로그인시 회원정보 있으면 내리고, 아니면 에러 처리")
//    public void login(){
//        String token = "token";
//
//        SocialLogin socialLogin = socialLoginRepo.findByIdToken(token).orElse(null);
//        if(socialLogin==null) throw new BusinessException(ErrorCode.NOT_FOUND); //회원정보없음 처리
//
//        User user = socialLogin.getUser();
//        //유저 정보 내려주기
//    }
//    @DisplayName("생일/성별 정보가 유무에 따라 프로필 온보딩 진행")
//    public void startOnboarding(){
//        LocalDate birth = LocalDate.parse("1999-10-10");
//        String sex = "여";
//        User user = User.builder().build();
//
//        if(user.getBirth().equals(null) || user.getSex().equals(null)){
//            //프로필 온보딩 시작
//            profileOnboarding(birth, sex);
//        }else{
//            //여행 온보딩 시작
//            travelOnboarding();
//        }
//    }
//
//    @Test
//    @DisplayName("프로필 온보딩, 성별, 생년월일 업데이트")
//    public void profileOnboarding(LocalDate birth, String sex){
//        User user = User.builder()
//                .build();
//        Assertions.assertEquals(null, user.getSex());
//
//        user.updateUserProfile(birth, sex);
//        Assertions.assertEquals("여", user.getSex());
//        userRepo.save(user);
//    }
//
//    @Transactional
//    @Test
//    public void travelOnboarding(String destination, LocalDate startDate, LocalDate endDate, String travelWith, String role, ) {
//        // 1) 여행정보 데이터 인서트
//
//        long travelThemeId = 1L;
//        long userId = 1L;
//        String destination = "목적지";
//        LocalDate startDate = LocalDate.parse("2023-10-10");
//        LocalDate endDate = LocalDate.parse("2023-11-10");
//        String travelWith = "혼자왔어요";
//        TeamMemberRole teamMemberRole = TeamMemberRole.valueOf("READER");
//        User user = User.builder().id(userId).build();
//        TravelTheme travelTheme = TravelTheme.builder()
//                .id(travelThemeId).build();
//
//
//        Travel travel = Travel.builder()
//                .reader(user)
//                .destination(destination)
//                .title(makeTravelName(destination, startDate))
//                .travelTheme(travelTheme)
//                .startDate(startDate)
//                .endDate(endDate)
//                .build();
//        travelRepo.save(travel);
//
//        TravelParticipate travelParticipate = TravelParticipate.builder()
//                .travel(travel)
//                .teamMember(user)
//                .teamMemberRole(teamMemberRole)
//                .build();
//        travelParticipateRepo.save(travelParticipate);
//
//        Assertions.assertEquals("23년 10월 목적지", makeTravelName(destination, startDate));
//
//    }
//
//    //여행 이름 만들어주기
//    public String makeTravelName(String destination, LocalDate startDate){
//        // 날짜를 원하는 형식으로 포맷
//        String formattedDate = startDate.format(DateTimeFormatter.ofPattern("yy년 MM월"));
//
//        // 최종 문자열 생성
//        String result = String.format("%s %s", formattedDate, destination);
//        return result;
//    }
//
//    public String makeTravelTitle(LocalDate startDate, String destination){
//        // 여행지, 몇월인지 확인후 00년00월 여행지
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy년 MM월");
//        String formattedDate = startDate.format(formatter);
//
//        // 결과 문자열 생성
//        String title = formattedDate + " " + destination;
//        return title;
//    }
//
//
//}
