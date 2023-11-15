package com.youngsquad.mission.domain.TeamMission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMissionStatusRepo extends JpaRepository<TeamMissionStatus, Long> {
    List<TeamMissionStatus> getTeamMissionStatusesByTeamMissionMemberIdAndStatus(long memberId, String status);
    TeamMissionStatus getTeamMissionStatusByTeamMissionMemberIdAndStatus(long memberId, String status);
    TeamMissionStatus getTeamMissionStatusById(long id);

}
