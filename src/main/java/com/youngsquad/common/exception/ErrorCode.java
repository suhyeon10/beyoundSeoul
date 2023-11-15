package com.youngsquad.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    NOT_FOUND(HttpStatus.BAD_REQUEST.value(),"request형식 확인필요"),
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST.value(),"이미 완료되거나 종료된 미션입니다.");
    private final int status;
    private final String msg;

    ErrorCode(int status, String msg){
        this.status = status;
        this.msg = msg;
    }
    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.msg;
    }

}
