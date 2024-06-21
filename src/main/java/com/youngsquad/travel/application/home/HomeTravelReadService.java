package com.youngsquad.travel.application.home;

import com.youngsquad.common.s3.S3Service;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.TravelParticipate;
import com.youngsquad.travel.domain.service.home.HomeProfileRepository;
import com.youngsquad.travel.domain.service.TravelParticipateRepository;
import com.youngsquad.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomeTravelReadService {
    private final TravelParticipateRepository travelParticipateRepository;
    private final HomeProfileRepository homeProfileRepository;
    private final S3Service s3Service;


    public Travel getUserTravel(User user) {
        LocalDate today = LocalDate.now();
        log.info("오늘은 :: "+today);
        Travel latestTravel = homeProfileRepository.findLatestTravelByUserIdAndTodayIncluded(user.getId(), today);
        return latestTravel;
    }

    public String getTravelMemberImage(User user) {
        return s3Service.getDownloadPresignedURL(user.getImage());
    }

    public List<String> getTravelMemberImageList(Travel travel, User user) {
        List<User> users = getTravelUsers(travel);
        return users.stream()
                .filter(u -> !u.equals(user))
                .map(this::getTravelMemberImage)
                .collect(Collectors.toList());
    }

    public List<User> getTravelUsers(Travel travel) {
        return travelParticipateRepository.findByTravel(travel)
                .stream()
                .map(TravelParticipate::getTeamMember)
                .collect(Collectors.toList());
    }

    private boolean isTodayIncludedInTravel(Travel travel) {
        LocalDate today = LocalDate.now();
        return (travel.getStartDate().isEqual(today) || travel.getStartDate().isBefore(today)) &&
                (travel.getEndDate().isEqual(today) || travel.getEndDate().isAfter(today));
    }


}
