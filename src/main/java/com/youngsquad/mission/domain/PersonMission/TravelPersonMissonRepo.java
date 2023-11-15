package com.youngsquad.mission.domain.PersonMission;

import com.youngsquad.mission.domain.TeamMission.TravelTeamMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelPersonMissonRepo extends JpaRepository<
        TravelPersonMission, Long> {
}
