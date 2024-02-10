package com.youngsquad.travel.presentation;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import com.youngsquad.travel.application.TravelThemeReadService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/themes")
@RequiredArgsConstructor
public class TravelThemeReadController extends BaseController {

    private final TravelThemeReadService travelThemeReadService;
    @ApiOperation(
            value = "여행 테마 조회 API"
    )    @GetMapping
    public CommonResult read() {
        return result(travelThemeReadService.readTravelTheme(travelThemeReadService));
    }

}
