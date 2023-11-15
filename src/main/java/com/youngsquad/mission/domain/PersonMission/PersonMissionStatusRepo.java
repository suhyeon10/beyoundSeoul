package com.youngsquad.mission.domain.PersonMission;

import com.youngsquad.mission.domain.TeamMission.TeamMissionMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonMissionStatusRepo extends JpaRepository<
        PersonMissionStatus, Long> {

    PersonMissionStatus getFirstByTeamMissionMemberIdAndStatus(long teamMissionMember, String status);
    List<PersonMissionStatus> getPersonMissionStatusesByTeamMissionMemberIdAndStatus(long teamMissionMember, String status);

    PersonMissionStatus getPersonMissionStatusById(long id);
}
