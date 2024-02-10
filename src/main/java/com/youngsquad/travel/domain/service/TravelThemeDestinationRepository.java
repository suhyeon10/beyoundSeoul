package com.youngsquad.travel.domain.service;

import com.youngsquad.travel.domain.model.TravelTheme;
import com.youngsquad.travel.domain.model.TravelThemeDestination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelThemeDestinationRepository extends JpaRepository<TravelThemeDestination, Long> {
    List<TravelThemeDestination> findByTravelTheme(TravelTheme travelTheme);
}
