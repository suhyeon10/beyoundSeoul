package com.youngsquad.travel.domain.service;

import com.youngsquad.travel.domain.model.Travel;

public interface HomeProfileRepository {
    Travel findLatestTravelByUserId(Long userId);

}
