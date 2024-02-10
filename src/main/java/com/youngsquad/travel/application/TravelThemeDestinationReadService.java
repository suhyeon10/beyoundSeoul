package com.youngsquad.travel.application;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.common.s3.S3Service;
import com.youngsquad.travel.domain.model.TravelTheme;
import com.youngsquad.travel.domain.model.TravelThemeDestination;
import com.youngsquad.travel.domain.service.TravelThemeDestinationRepository;
import com.youngsquad.travel.domain.service.TravelThemeRepository;
import com.youngsquad.travel.presentation.response.TravelThemeDestinationReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelThemeDestinationReadService {
    private final TravelThemeDestinationRepository travelThemeDestinationRepository;
    private final TravelThemeRepository travelThemeRepository;
    private final S3Service s3Service;

    public TravelThemeDestinationReadResponse readTravelThemeDestination(long themeId, TravelThemeDestinationReadService travelThemeDestinationReadService){
        TravelTheme travelTheme = travelThemeRepository.findById(themeId).orElseThrow(
                ()-> new BusinessException(ErrorCode.THEME_NOT_FOUND)
        );
        List<TravelThemeDestination> travelThemeDestinationList = travelThemeDestinationRepository.findByTravelTheme(travelTheme);

        return TravelThemeDestinationReadResponse.makeResponse(travelThemeDestinationList, travelThemeDestinationReadService);
    }
    public String getDestinationImage(TravelThemeDestination travelThemeDestination){
        return s3Service.getDownloadPresignedURL(travelThemeDestination.getRoute());
    }
}
