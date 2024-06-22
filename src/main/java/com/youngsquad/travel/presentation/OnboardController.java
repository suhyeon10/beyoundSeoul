package com.youngsquad.travel.presentation;


import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import com.youngsquad.travel.application.onboard.OnboardService;
import com.youngsquad.travel.presentation.request.LeaderOnboardRequest;
import com.youngsquad.travel.presentation.request.MateOnboardRequest;
import com.youngsquad.travel.presentation.request.OnboardRequest;
import com.youngsquad.travel.presentation.request.SoloOnboardRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/onboard")
@RequiredArgsConstructor
public class OnboardController extends BaseController {

    private final OnboardService onboardService;
    @ApiOperation(
            value = "온보드 완료 후 호출",
            notes = "온보드 완료 후 호출시 미션 할당"
    )    @PostMapping(path = "/complete")
    public CommonResult completeOnboard(@RequestBody OnboardRequest request) {
        onboardService.completeOnboard(request);
        return success();

    }

    @ApiOperation(
            value = "혼자 온보드 완료 후 호출",
            notes = "혼자 온보드 완료 후 호출시 미션 할당"
    )
    @PostMapping(path = "/complete/solo")
    public CommonResult completeSoloOnboard(@RequestBody SoloOnboardRequest request) {
        onboardService.completeOnboard(request);
        return success();
    }

    @ApiOperation(
            value = "리더 온보드 완료 후 호출",
            notes = "리더 온보드 완료 후 호출시 미션 할당"
    )
    @PostMapping(path = "/complete/leader")
    public CommonResult completeLeaderOnboard(@RequestBody LeaderOnboardRequest request) {
        onboardService.completeOnboard(request);
        return success();
    }

    @ApiOperation(
            value = "메이트 온보드 완료 후 호출",
            notes = "메이트 온보드 완료 후 호출시 미션 할당"
    )
    @PostMapping(path = "/complete/mate")
    public CommonResult completeMateOnboard(@RequestBody MateOnboardRequest request) {
        onboardService.completeOnboard(request);
        return success();
    }

}
