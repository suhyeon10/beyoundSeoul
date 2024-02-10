package com.youngsquad.travel.application;

import com.youngsquad.common.s3.S3Service;
import com.youngsquad.travel.domain.model.TravelTheme;
import com.youngsquad.travel.domain.service.TravelThemeRepository;
import com.youngsquad.travel.presentation.response.TravelThemeReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelThemeReadService {
    private final TravelThemeRepository travelThemeRepository;
    private final S3Service s3Service;
    public TravelThemeReadResponse readTravelTheme(TravelThemeReadService travelThemeReadService){
        List<TravelTheme> travelThemeList = travelThemeRepository.findAll();
        return TravelThemeReadResponse.makeResponse(travelThemeList, travelThemeReadService);
    }

    public String getThemeImage(TravelTheme travelTheme){
        return s3Service.getDownloadPresignedURL(travelTheme.getRoute());
    }

}
