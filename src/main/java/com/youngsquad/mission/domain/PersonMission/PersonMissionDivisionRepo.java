package com.youngsquad.mission.domain.PersonMission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonMissionDivisionRepo  extends JpaRepository<PersonMissionDivision, Long> {
    List<PersonMissionDivision> getPersonMissionDivisionsByDestination(String destination);
}
