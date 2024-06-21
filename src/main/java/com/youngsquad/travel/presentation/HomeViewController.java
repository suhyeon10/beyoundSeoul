package com.youngsquad.travel.presentation;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import com.youngsquad.travel.application.home.HomeViewService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/home")
@RequiredArgsConstructor
public class HomeViewController extends BaseController {

    private final HomeViewService homeViewService;

    @ApiOperation(
            value = "홈페이지 뷰 데이터 조회",
            notes = "홈페이지 뷰 데이터 조회"
    )    @GetMapping(path = "/view")
    public CommonResult viewHome(@RequestParam(value="uid") long uid) {
        return result(homeViewService.viewHome(uid));
    }
}
