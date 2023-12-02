package com.youngsquad.travel.controller;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import com.youngsquad.travel.service.MateCodeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/matecode")
@RequiredArgsConstructor
public class MateCodeController extends BaseController {

    private final MateCodeService mateCodeService;
    @ApiOperation(
            value = "메이트 코드 조회",
            notes = "여행 ID로 메이트 코드 조회"
    )    @GetMapping(path = "/view")
    public CommonResult viewMateCode(@RequestParam int travelId) {
        return result(mateCodeService.viewMateCode(travelId));
    }

    @ApiOperation(
            value = "메이트 코드 등록",
            notes = "메이트 코드로 팀원 등록"
    )    @PostMapping(path = "/register")
    public CommonResult registerTeamToTravel(@RequestParam int uid, String code) {
        mateCodeService.registerTeamToTravel(uid, code);
        return success();
    }

    @ApiOperation(
            value = "메이트 코드 새로 발급받기",
            notes = "메이트 코드 새로 발급받기"
    )    @PostMapping(path = "/new")
    public CommonResult getNewMateCode(@RequestParam int travelId) {
        return result(mateCodeService.getNewMateCode(travelId));
    }
}
