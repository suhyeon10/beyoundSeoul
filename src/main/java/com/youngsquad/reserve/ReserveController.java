package com.youngsquad.reserve;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import com.youngsquad.home.HomeService;
import com.youngsquad.reserve.dto.BusReq;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/reserve")
@RequiredArgsConstructor
public class ReserveController extends BaseController {

    private final ReserveService reserveService;

    @ApiOperation(
            value = "셔틀버스 예약 모달 데이터 조회",
            notes = "셔틀버스 예약 데이터 조회"
    )    @GetMapping(path = "/modal")
    public CommonResult viewModal(@RequestBody BusReq busReq) {
        return result(reserveService.getModal(busReq));
    }

    @ApiOperation(
            value = "버스 예약  내역 조회",
            notes = "버스 예약 내역 조회"
    )    @GetMapping(path = "/busModal")
    public CommonResult getBusReserveModal() {
        return result(reserveService.getBusReserveModal(0));
    }
}
