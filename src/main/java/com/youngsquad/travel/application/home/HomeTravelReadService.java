package com.youngsquad.travel.application.home;

import com.youngsquad.common.s3.S3Service;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.TravelParticipate;
import com.youngsquad.travel.domain.service.TravelParticipateRepository;
import com.youngsquad.travel.presentation.response.HomeResponse;
import com.youngsquad.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeTravelReadService {
    private final TravelParticipateRepository travelParticipateRepository;
    private final S3Service s3Service;

    public HomeResponse.TravelResponse makeResponse(Travel travel) {
        return HomeResponse.makeTravelResponse(travel, this);
    }

    public Travel getUserTravel(User user) {
        return travelParticipateRepository.findByTeamMemberOrderByCreateDateDesc(user)
                .stream()
                .map(TravelParticipate::getTravel)
                .filter(travel -> isBetweenDates(LocalDate.now(), travel.getStartDate(), travel.getEndDate()))
                .findFirst().get();
    }

    private boolean isBetweenDates(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    public String getTravelMemberImage(User user) {
        return s3Service.getDownloadPresignedURL(user.getImage());
    }

    public List<String> getTravelMemberImageList(Travel travel) {
        List<User> users = getTravelUsers(travel);
        return users.stream()
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
