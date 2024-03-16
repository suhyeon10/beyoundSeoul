package com.youngsquad.home.travel;

import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.TravelParticipate;
import com.youngsquad.travel.domain.service.home.HomeProfileRepository;
import com.youngsquad.travel.domain.service.TravelParticipateRepository;
import com.youngsquad.travel.domain.service.TravelRepository;
import com.youngsquad.user.domain.model.User;
import com.youngsquad.user.domain.service.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

@DataJpaTest
@DisplayName("홈 페이지의 데이터를 조회하는 테스트")
public class HomeViewTest {
    @Autowired(required = false)
    private HomeProfileRepository homeProfileRepository;
    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TravelParticipateRepository travelParticipateRepository;

    @Test
    public void readHome(){

        long userId = 1L;
        makeData();
        Travel travel = homeProfileRepository.findLatestTravelByUserId(userId);
//        List<User> teamMembers = homeProfileRepository.findTeamMembersByTravel(travel);

        Assertions.assertEquals(travel.getId(), 3L);

    }

    public void makeData(){
        //여행 데이터 생성
        Travel travel = travelRepository.save(
                Travel.builder()
                        .id(3L)
                        .createAt(LocalDateTime.now())
                        .build()
        );

        //유저 데이터 생성
        User user1 = User.builder().id(1L).build();
        User user2 = User.builder().id(2L).build();
        userRepository.save(user1);
        userRepository.save(user2);


        //여행 참여 데이터 생성
        travelParticipateRepository.save(TravelParticipate.builder()
                .teamMember(user1)
                .travel(travel)
                .build());
        travelParticipateRepository.save(TravelParticipate.builder()
                .teamMember(user2)
                .travel(travel)
                .build());

    }

}
