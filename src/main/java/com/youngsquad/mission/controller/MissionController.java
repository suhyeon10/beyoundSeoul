package com.youngsquad.mission.controller;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/mission")
@RequiredArgsConstructor
public class MissionController extends BaseController{

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
    )    @GetMapping(path = "/refresh")
    public CommonResult refreshMission(@RequestParam long uid, long missionId, String missionType) {
        return success();
    }




}
