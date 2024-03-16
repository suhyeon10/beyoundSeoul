package com.youngsquad.mission.presentation;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import com.youngsquad.mission.application.MissionRefreshService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/mission")
@RequiredArgsConstructor
public class MissionController extends BaseController{
    private final MissionRefreshService missionRefreshService;
    @ApiOperation(
            value = "미션 상세페이지 모달 데이터 조회",
            notes = "미션 상세페이지 데이터 조회"
    )    @GetMapping(path = "/detailModal")
    public CommonResult detailModal(@RequestParam long missionId, String missionType) {
        return success();
    }

    @ApiOperation(
            value = "미션 새로고침",
            notes = "미션 새로고침"
    )    @PostMapping(path = "/refresh")
    public CommonResult refreshMission(@RequestParam long missionId) {
        missionRefreshService.missionRefresh(missionId);
        return success();
    }




}
