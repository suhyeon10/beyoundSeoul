package com.youngsquad.travel.application;

import com.youngsquad.mission.domain.Mission;
import com.youngsquad.travel.application.matecode.MateCodeService;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.TravelRoleType;
import com.youngsquad.travel.domain.model.TravelWithType;
import com.youngsquad.travel.domain.model.mission.MissionCategory;
import com.youngsquad.travel.domain.model.mission.TravelMissionSample;
import com.youngsquad.travel.domain.service.mission.TravelMissionSampleRepository;
import com.youngsquad.travel.presentation.request.OnboardRequest;
import com.youngsquad.travel.domain.service.TravelRepository;
import com.youngsquad.user.application.UserService;
import com.youngsquad.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OnboardService {

    private final TravelRepository travelRepository;
    private final OnboardMissionService onboardMissionService;
    private final OnboardProfileUpdateService onboardProfileUpdateService;

    @Transactional
    public void completeOnboard(OnboardRequest onboardRequest){

        // 0) 프로필 업데이트
        onboardProfileUpdateService.updateProfile(onboardRequest.getUid(),
                onboardRequest.getBirth(),
                onboardRequest.getSex());

        // 1) 리더/ 혼자 여행시 , 여행 생성
        // 2) 미션 샘플 데이터에서 조회후, 미션 생성
        if(!onboardRequest.getTravelWith().equals(TravelRoleType.MATE.getDetail())) {
            Travel travel = createTravel(onboardRequest);

            onboardMissionService.createMission(onboardRequest, travel);
        }

    }

    private Travel createTravel(OnboardRequest onboardRequest){
        Travel travel = Travel.makeEntity(
                onboardRequest.getDestination(),
                onboardRequest.getTravelEndDate(),
                onboardRequest.getTravelStartDate(),
                onboardRequest.getUid(),
                TravelWithType.getByDetail(onboardRequest.getTravelWith()),
                makeTravelTitle(onboardRequest.getTravelStartDate(), onboardRequest.getDestination()),
                onboardRequest.getThemaId()
        );

        travelRepository.save(travel);
        return travel;
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

