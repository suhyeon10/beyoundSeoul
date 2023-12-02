package com.youngsquad.onboard;

import com.youngsquad.mission.service.MissionService;
import com.youngsquad.onboard.dto.OnboardReq;
import com.youngsquad.travel.domain.TravelDetail;
import com.youngsquad.travel.domain.TravelDetailRepo;
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

    private final TravelDetailRepo travelDetailRepo;
    private final MissionService missionService;
    @Transactional
    public void completeOnboard(OnboardReq onboardReq){
        // 1) 여행정보 데이터 인서트
        String role = onboardReq.getRole();

        if(role.equals("reader")) {
            //리더 선택 or 혼자 왔어여
            TravelDetail travelDetail = TravelDetail.builder()
                    .travelWith(onboardReq.getTravelWith())
                    .readerId((long) onboardReq.getUid())
                    .startDate(onboardReq.getTravelStartDate())
                    .endDate(onboardReq.getTravelEndDate())
                    .transport(onboardReq.getTransport())
                    .destination(onboardReq.getDestination())
                    .title(makeTravelTitle(onboardReq.getTravelStartDate(),
                            onboardReq.getDestination()))
                    .build();
            travelDetailRepo.save(travelDetail);
            // 2) 미션 데이터 생성
            // 여행지에 따라 미션 랜덤 추가
//            missionService.createMission(onboardReq.getDestination(),
//                    travelDetail,
//                    uid,
//                    onboardReq.getTravelStartDate(),
//                    onboardReq.getTravelEndDate());
            // 3) 공동멤버팀에 리더 데이터 추가
//            missionService.insertTeamMissionMember(uid, travelDetail);
        }

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
