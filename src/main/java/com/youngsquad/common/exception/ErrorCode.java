package com.youngsquad.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(),"존재하지 않는 유저 아이디 입니다"),
    USER_ID_TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST.value(),"TOKEN ID 및 SNS를 보내주세요"),

    NOT_FOUND(HttpStatus.BAD_REQUEST.value(),"request형식 확인필요"),
    TRAVEL_NOT_FOUND(HttpStatus.BAD_REQUEST.value(),"존재하는 여행이 없습니다."),
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST.value(),"이미 완료되거나 종료된 미션입니다."),
    MATE_CODE_NOT_FOUND(HttpStatus.BAD_REQUEST.value(),"여행에 존재하는 메이트 코드가 없습니다."),
    THEME_NOT_FOUND(HttpStatus.BAD_REQUEST.value(),"해당 테마 ID가 존재하지 않습니다."),
    TRAVEL_MISSION_SAMPLE_NOT_FOUND(HttpStatus.BAD_REQUEST.value(),"해당 미션 샘플 데이터가 존재하지 않습니다");
    ;
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
