package com.youngsquad.mission.domain.TeamMission;

import com.youngsquad.travel.domain.TravelDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamMissionMemberRepo extends JpaRepository<TeamMissionMember, Long> {
    TeamMissionMember getFirstByTravelIdAndMemberId(long travelId, long memberId);
    TeamMissionMember getTeamMissionMemberById(long id);
    List<TeamMissionMember> getTeamMissionMemberByMemberId(long memberId);
    Optional<TeamMissionMember> getTopByMemberIdOrderByIdDesc(long memberId);

}
