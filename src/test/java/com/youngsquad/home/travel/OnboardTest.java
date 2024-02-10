package com.youngsquad.home.travel;

import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.TravelWithType;
import com.youngsquad.travel.domain.service.TravelRepository;
import com.youngsquad.travel.presentation.request.OnboardRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@DataJpaTest
public class OnboardTest {
    @Autowired
    private TravelRepository travelRepository;

    @Test
    public void createTravel(){
        String destination = "목적지";
        LocalDate travelEndDate = LocalDate.parse("2024-02-10");
        LocalDate travelStartDate = LocalDate.parse("2024-02-12");
        long uid = 1L;
        String travelType = "여행 메이트와 함께";

        Travel travel = Travel.makeEntity(
                destination,
                travelEndDate,
                travelStartDate,
                uid,
                TravelWithType.getByDetail(travelType),
        makeTravelTitle(travelStartDate, destination),
                1L);

        travelRepository.save(travel);
        Assertions.assertEquals(travelRepository.count(),1);

    }

    public String makeTravelTitle(LocalDate startDate, String destination){
        // 여행지, 몇월인지 확인후 00년00월 여행지
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy년 MM월");
        String formattedDate = startDate.format(formatter);

        // 결과 문자열 생성
        String title = formattedDate + " " + destination;
        return title;
    }
}
