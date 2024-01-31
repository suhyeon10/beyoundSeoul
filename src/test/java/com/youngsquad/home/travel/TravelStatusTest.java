//package com.youngsquad.home.travel;
//
//import com.youngsquad.common.exception.BusinessException;
//import com.youngsquad.common.exception.ErrorCode;
//import com.youngsquad.mission.domain.TeamMission.TeamMissionMember;
//import com.youngsquad.travel.domain.model.Travel;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.time.LocalDate;
//
//@DataJpaTest
//public class TravelStatusTest {
//
//    @Test
//    public void 팀미션_널_테스트() {
//        TeamMissionMember teamMissionMember = null;
//        Assertions.assertEquals(true, isChkNullOrBeforeTravel(teamMissionMember));
//    }
//    @Test
//    public void 여행기간중에_해당하는지_테스트() {
//        LocalDate start = LocalDate.parse("2023-11-01");
//        LocalDate end = LocalDate.parse("2023-11-10");
//
//        Assertions.assertEquals(true, isTodayDuringTravel(start,end));
//    }
//    @Test
//    public void BEFORE_ONBOARD_테스트(){
//        LocalDate start = LocalDate.parse("2023-11-01");
//        LocalDate end = LocalDate.parse("2023-11-10");
//
//
//        TeamMissionMember teamMissionMember = TeamMissionMember.builder()
//                .travel(
//                        Travel.builder()
//                                .startDate(start)
//                                .endDate(end)
//                                .build()
//                )
//                .build();
//        Assertions.assertEquals("BEFORE_TRAVEL_START", getTravelStatus(teamMissionMember));
//
//    }
//
//    private String getTravelStatus(TeamMissionMember teamMissionMember){
//
//
//        if (isAfterTravel(teamMissionMember)) {
//            return "AFTER_TRAVEL";
//        } if (isBeforeTravelStart(teamMissionMember)) {
//            return "BEFORE_TRAVEL_START";
//        } if (isDuringTravel(teamMissionMember)) {
//            return "DURING_TRAVEL";
//        }
//        throw new BusinessException(ErrorCode.NOT_FOUND);
//    }
//
//
//    //여행이 끝났을때
//    private Boolean isAfterTravel(TeamMissionMember teamMissionMember){
//        //  여행 끝 < 오늘
//        return getToday().isAfter(teamMissionMember.getTravel().getEndDate());
//    }
//
//    //여행이 시작하지 않았을 때
//    // 오늘 < 여행 시작
//    private Boolean isBeforeTravelStart(TeamMissionMember teamMissionMember){
//        return getToday().isBefore(teamMissionMember.getTravel().getStartDate());
//    }
//    //여행이 진행중인 경우
//    private Boolean isDuringTravel(TeamMissionMember teamMissionMember){
//        return !getToday().isBefore(teamMissionMember.getTravel().getStartDate()) && !getToday().isAfter(teamMissionMember.getTravel().getEndDate());
//    }
//
//    private LocalDate getToday(){
//        LocalDate today = LocalDate.parse("2023-10-11");
//        return today;
//    }
//    private Boolean isBeforeTravel(LocalDate startDate) {
//
//        return getToday().isBefore(startDate);
//    }
//
//    private Boolean isTodayDuringTravel(LocalDate startDate, LocalDate endDate) {
//        return !getToday().isBefore(startDate) && !getToday().isAfter(endDate);
//    }
//
//    private Boolean isChkNullOrBeforeTravel(TeamMissionMember teamMissionMember){
//        return teamMissionMember == null || isBeforeTravel(teamMissionMember.getTravel().getEndDate());
//    }
//}
