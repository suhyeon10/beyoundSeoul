package com.youngsquad.travel.application.matecode;

import com.youngsquad.travel.domain.model.TeamMemberRole;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.TravelParticipate;
import com.youngsquad.travel.domain.service.TravelParticipateRepository;
import com.youngsquad.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelParticipateService {
    private final TravelParticipateRepository travelParticipateRepository;
    public void saveTeamMissionMember(Travel travelDetail, User user) {
        TravelParticipate travelParticipate = TravelParticipate.from(user, travelDetail, TeamMemberRole.MEMBER);
        travelParticipateRepository.save(travelParticipate);
    }

    public void saveTeamMissionReader(Travel travelDetail, User user) {
        TravelParticipate travelParticipate = TravelParticipate.from(user, travelDetail, TeamMemberRole.READER);
        travelParticipateRepository.save(travelParticipate);
    }
}
