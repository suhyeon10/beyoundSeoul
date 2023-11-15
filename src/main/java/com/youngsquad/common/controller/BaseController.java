package com.youngsquad.common.controller;

import com.youngsquad.common.Response.CommonResult;
import com.youngsquad.common.Response.ResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class BaseController {
    @Autowired
    private ResponseService responseService;

    public CommonResult success()
    {
        return responseService.getSuccessResult();
    }
    public CommonResult result(Object data)
    {
        return responseService.getSingleResult(data);
    }


}
