package com.youngsquad.mission.presentation;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import com.youngsquad.mission.service.SquareService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/square")
@RequiredArgsConstructor
public class SquareController extends BaseController {

    private final SquareService squareService;
    @ApiOperation(
            value = "미션 광장 데이터 조회",
            notes = "미션 광장 데이터 조회"
    )    @GetMapping(path = "/detailModal")
    public CommonResult getSquareData() {
        return result(squareService.getSquareData(0));
    }
}
