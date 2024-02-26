package com.youngsquad.travel.application.home;

import com.youngsquad.common.s3.S3Service;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.TravelParticipate;
import com.youngsquad.travel.domain.service.home.HomeProfileRepository;
import com.youngsquad.travel.domain.service.TravelParticipateRepository;
import com.youngsquad.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeTravelReadService {
    private final TravelParticipateRepository travelParticipateRepository;
    private final HomeProfileRepository homeProfileRepository;
    private final S3Service s3Service;


    public Travel getUserTravel(User user) {
        return homeProfileRepository.findLatestTravelByUserId(user.getId());
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


}
