package com.youngsquad.mission.domain.TeamMission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMissionDivisionRepo extends JpaRepository<TeamMissionDivision, Long> {
    List<TeamMissionDivision> getTeamMissionDivisionsByDestination(String destination);
}
