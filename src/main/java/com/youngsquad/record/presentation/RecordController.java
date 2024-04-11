package com.youngsquad.record.presentation;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import com.youngsquad.record.application.RecordCreateService;
import com.youngsquad.record.application.RecordReadService;
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

    private final RecordCreateService recordCreateService;
    private final RecordReadService recordReadService;
    @ApiOperation(
            value = "미션 기록 생성하기",
        notes = "미션 기록 생성하기"
    )    @PostMapping(path = "/create")
        public CommonResult createRecord(CreateRecordRequest request) throws IOException {
            recordCreateService.create(request);
            return success();
    }

    @ApiOperation(
            value = "기록 피드 처음 조회",
            notes = "미션 기록 조회"
    )    @PostMapping(path = "/viewLatest")
    public CommonResult viewRecordByLatestTravel(@RequestParam long cursorId, long uid, int size ) throws IOException {

        return result(recordReadService.getRecordsByLatestTravel(cursorId, size, uid));
    }

    @ApiOperation(
            value = "기록 피드 조회",
            notes = "미션 기록 조회"
    )    @PostMapping(path = "/view")
    public CommonResult viewRecordByTravelId(@RequestParam long travelId, long cursorId, long uid, int size ) throws IOException {

        return result(recordReadService.getRecordsByTravel(travelId, cursorId, uid, size));
    }

    @ApiOperation(
            value = "기록 피드 조회",
            notes = "기록피드의 여행이름 조회"
    )    @PostMapping(path = "/travelName")
    public CommonResult viewRecordByTravelId(@RequestParam long uid) throws IOException {

        return result(recordReadService.getTravelNameFromRecord( uid));
    }

}
