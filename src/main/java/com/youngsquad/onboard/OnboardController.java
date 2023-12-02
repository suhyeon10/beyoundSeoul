package com.youngsquad.onboard;


import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import com.youngsquad.onboard.dto.OnboardReq;
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
    public CommonResult completeOnboard(@RequestBody OnboardReq onboardReq) {
        onboardService.completeOnboard(onboardReq);
        return success();
    }

}
