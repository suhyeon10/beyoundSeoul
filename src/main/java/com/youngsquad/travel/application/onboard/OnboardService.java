package com.youngsquad.travel.application.onboard;

import com.youngsquad.travel.application.matecode.MateCodeService;
import com.youngsquad.travel.application.matecode.TravelParticipateService;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.TravelRoleType;
import com.youngsquad.travel.domain.model.TravelWithType;
import com.youngsquad.travel.presentation.request.LeaderOnboardRequest;
import com.youngsquad.travel.presentation.request.MateOnboardRequest;
import com.youngsquad.travel.presentation.request.OnboardRequest;
import com.youngsquad.travel.domain.service.TravelRepository;
import com.youngsquad.travel.presentation.request.SoloOnboardRequest;
import com.youngsquad.user.application.UserService;
import com.youngsquad.user.domain.model.User;
import com.youngsquad.user.domain.service.UserRepository;
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
    private final UserService userService;

    private final TravelRepository travelRepository;
    private final OnboardMissionService onboardMissionService;
    private final OnboardProfileUpdateService onboardProfileUpdateService;
    private final TravelParticipateService travelParticipateService;
    private final MateCodeService mateCodeService;

    @Transactional
    public void completeOnboard(OnboardRequest onboardRequest){
        log.info("uid :: "+onboardRequest.getUid());
        User user = userService.findUser(onboardRequest.getUid());
        // 프로필 업데이트
        onboardProfileUpdateService.updateProfile(user,
                onboardRequest.getBirth(),
                onboardRequest.getSex());

        if(!onboardRequest.getTravelWith().equals(TravelRoleType.MATE.getDetail())) {
            Travel travel = createTravel(onboardRequest, TravelWithType.getByDetail(onboardRequest.getTravelWith()));
            // 리더 참가 데이터
            travelParticipateService.saveTeamMissionReader(travel, user);
            // 미션 생성
            onboardMissionService.createMission(onboardRequest, travel);
        }

    }
    @Transactional
    public void completeOnboard(SoloOnboardRequest request) {
        // Solo onboard logic

        log.info("uid :: "+request.getUid());
        User user = userService.findUser(request.getUid());
        // 프로필 업데이트
        onboardProfileUpdateService.updateProfile(user,
                request.getBirth(),
                request.getSex());
        // 여행 생성
        Travel travel = createTravel(request, TravelWithType.ALONE);
        // 여행 미션 생성
        onboardMissionService.createMission(request, travel);

    }
    @Transactional
    public void completeOnboard(LeaderOnboardRequest request) {
        // Leader onboard logic

        log.info("uid :: "+request.getUid());
        User user = userService.findUser(request.getUid());
        // 프로필 업데이트
        onboardProfileUpdateService.updateProfile(user,
                request.getBirth(),
                request.getSex());
        // 여행 생성
        Travel travel = createTravel(request, TravelWithType.TOGETHER);
        // 리더 참가 데이터
        travelParticipateService.saveTeamMissionReader(travel, user);
        // 여행 미션 생성
        onboardMissionService.createMission(request, travel);

    }
    @Transactional
    public void completeOnboard(MateOnboardRequest request) {
        // Mate onboard logic
        User user = userService.findUser(request.getUid());

        // 프로필 업데이트
        onboardProfileUpdateService.updateProfile(user,
                request.getBirth(),
                request.getSex());

        // 여행 메이트 등록..
        mateCodeService.registerTeamToTravel(user, request.getCode());
    }

    private Travel createTravel(OnboardRequest onboardRequest, TravelWithType travelWith){
        Travel travel = Travel.makeEntity(
                onboardRequest.getDestination(),
                onboardRequest.getTravelEndDate(),
                onboardRequest.getTravelStartDate(),
                onboardRequest.getUid(),
                travelWith,
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

