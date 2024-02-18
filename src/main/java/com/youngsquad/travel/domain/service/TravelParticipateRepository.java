package com.youngsquad.travel.domain.service;

import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.TravelParticipate;
import com.youngsquad.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TravelParticipateRepository extends JpaRepository<TravelParticipate, Long> {
    Optional<TravelParticipate> findTopByTeamMemberOrderByCreateDateDesc(User user);
    List<TravelParticipate> findByTeamMemberOrderByCreateDateDesc(User user);

    List<TravelParticipate> findByTravel(Travel travel);
}
