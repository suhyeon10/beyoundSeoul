package com.youngsquad.travel.presentation.response;

import com.youngsquad.travel.application.TravelThemeReadService;
import com.youngsquad.travel.domain.model.TravelTheme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Builder
public class TravelThemeReadResponse {
    private List<Theme> themes;
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Theme{
        private String themeName;
        private Long themeId;
        private String image;
    }

    public static TravelThemeReadResponse makeResponse(List<TravelTheme> travelThemes, TravelThemeReadService travelThemeReadService){
        return TravelThemeReadResponse.builder()
                .themes(makeThemeList(travelThemes, travelThemeReadService))
                .build();
    }

    public static List<Theme> makeThemeList(List<TravelTheme> travelThemes, TravelThemeReadService travelThemeReadService){
        return travelThemes.stream().map(theme -> makeThemeResponse(theme, travelThemeReadService)).collect(Collectors.toList());
    }
    public static Theme makeThemeResponse(TravelTheme travelTheme, TravelThemeReadService travelThemeReadService){
        return Theme.builder()
                .themeId(travelTheme.getId())
                .themeName(travelTheme.getName())
                .image(travelThemeReadService.getThemeImage(travelTheme))
                .build();
    }

}
