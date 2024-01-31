package com.youngsquad.record.presentation;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import com.youngsquad.record.presentation.request.CreateRecordRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "/record")
@RequiredArgsConstructor
public class RecordController extends BaseController {

    @ApiOperation(
            value = "미션 기록 생성하기",
        notes = "미션 기록 생성하기"
    )    @PostMapping(path = "/create")
        public CommonResult createRecord(CreateRecordRequest createRecordReq) throws IOException {
            return success();
    }

    @ApiOperation(
            value = "미션 기록 조회",
            notes = "미션 기록 조회"
    )    @PostMapping(path = "/view")
    public CommonResult viewRecord(@RequestParam int uid) throws IOException {

        return success();
    }
}
