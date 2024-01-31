package com.youngsquad.travel.application;

import com.youngsquad.travel.presentation.request.OnboardRequest;
import com.youngsquad.travel.domain.service.TravelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class OnboardService {

    private final TravelRepository travelRepository;
    @Transactional
    public void completeOnboard(OnboardRequest onboardReq){
        // 1) 여행정보 데이터 인서트
        String role = onboardReq.getRole();

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
