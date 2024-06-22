package com.youngsquad.travel.presentation;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import com.youngsquad.travel.application.TravelThemeDestinationReadService;
import com.youngsquad.travel.application.TravelThemeReadService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/themes")
@RequiredArgsConstructor
public class TravelThemeDestinationReadController extends BaseController {
    private final TravelThemeDestinationReadService travelThemeDestinationReadService;
    @ApiOperation(
            value = "여행 테마별 여행지 조회 API"
    )    @GetMapping(path = "/destinations")
    public CommonResult read(@RequestParam(value="themeId") long themeId) {
        return result(travelThemeDestinationReadService.readTravelThemeDestination(themeId, travelThemeDestinationReadService));
    }
}
