package com.youngsquad.travel.presentation.response;

import com.youngsquad.travel.application.TravelThemeDestinationReadService;
import com.youngsquad.travel.domain.model.TravelThemeDestination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class TravelThemeDestinationReadResponse {
    private List<Destination> destinations;
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Destination{
        private String destination;
        private String detail;
        private String image;
    }
    public static TravelThemeDestinationReadResponse makeResponse(List<TravelThemeDestination> travelThemeDestinations, TravelThemeDestinationReadService travelThemeDestinationReadService){
        return TravelThemeDestinationReadResponse.builder()
                .destinations(makeDestinationList(travelThemeDestinations, travelThemeDestinationReadService))
                .build();
    }
    public static List<Destination> makeDestinationList(List<TravelThemeDestination> travelThemeDestinations, TravelThemeDestinationReadService travelThemeDestinationReadService){
        return travelThemeDestinations.stream().map(destination -> makeDestinationResponse(destination, travelThemeDestinationReadService)).collect(Collectors.toList());
    }
    public static Destination makeDestinationResponse(TravelThemeDestination travelThemeDestination, TravelThemeDestinationReadService travelThemeDestinationReadService){
        return Destination.builder()
                .destination(travelThemeDestination.getDestination())
                .detail(travelThemeDestination.getDetal())
                .image(travelThemeDestinationReadService.getDestinationImage(travelThemeDestination))
                .build();
    }
}
