package com.youngsquad.record.presentation;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.controller.BaseController;
import com.youngsquad.record.application.RecordCreateService;
import com.youngsquad.record.application.RecordReadService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    )
    @PostMapping(path = "/create")
    public CommonResult createRecord(
            @RequestParam(value="missionId") long missionId,
            @RequestParam(value="uid") long uid,
            @RequestParam(value="recordImage", required = false) MultipartFile recordImage,
            @RequestParam(value="recordComment", required = false) String recordComment
            ) throws IOException {
        recordCreateService.create(
                missionId,
                uid,
                recordComment,
                recordImage
        );
        return success();
    }

    @ApiOperation(
            value = "기록 피드 처음 조회",
            notes = "미션 기록 조회"
    )
    @PostMapping(path = "/viewLatest")
    public CommonResult viewRecordByLatestTravel(
            @RequestParam("cursorId") int cursorId,
            @RequestParam("uid") int uid,
            @RequestParam("size") int size
    ) throws IOException {
        log.info("uid: "+uid);

        return result(recordReadService.getRecordsByLatestTravel((long)cursorId, size, (long)uid));
    }

    @ApiOperation(
            value = "기록 피드 조회",
            notes = "미션 기록 조회"
    )
    @PostMapping(path = "/view")
    public CommonResult viewRecordByTravelId(
            @RequestParam("travelId") long travelId,
            @RequestParam("cursorId") long cursorId,
            @RequestParam("uid") long uid,
            @RequestParam("size") int size
    ) throws IOException {
        log.info("uid: "+uid);
        return result(recordReadService.getRecordsByTravel(travelId, cursorId, uid, size));
    }

    @ApiOperation(
            value = "기록 피드 조회",
            notes = "기록피드의 여행이름 조회"
    )
    @PostMapping(path = "/travelName")
    public CommonResult viewRecordByTravelId(
            @RequestParam("uid") long uid
    ) throws IOException {
        log.info("uid: "+uid);

        return result(recordReadService.getTravelNameFromRecord(uid));
    }
}
