package com.youngsquad.travel.service;

import com.youngsquad.travel.domain.TeamMemberRole;
import com.youngsquad.travel.domain.Travel;
import com.youngsquad.travel.domain.TravelParticipate;
import com.youngsquad.travel.domain.TravelParticipateRepo;
import com.youngsquad.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelParticipateService {
    private final TravelParticipateRepo travelParticipateRepo;
    public void saveTeamMissionMember(Travel travelDetail, User user) {
        TravelParticipate travelParticipate = TravelParticipate.from(user, travelDetail, TeamMemberRole.MEMBER);
        travelParticipateRepo.save(travelParticipate);
    }
}
