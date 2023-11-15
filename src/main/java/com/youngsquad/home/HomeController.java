package com.youngsquad.home;

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
@RequestMapping(value = "/home")
@RequiredArgsConstructor
public class HomeController extends BaseController {

    private final HomeService homeService;

    @ApiOperation(
            value = "홈페이지 뷰 데이터 조회",
            notes = "홈페이지 뷰 데이터 조회"
    )    @GetMapping(path = "/view")
    public CommonResult viewHome(@RequestParam long uid) {
        return result(homeService.viewHome(uid));
    }
}
