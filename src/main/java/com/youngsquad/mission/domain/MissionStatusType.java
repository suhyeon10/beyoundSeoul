package com.youngsquad.mission.domain;

public enum MissionStatusType {
    ONGOING("진행중"),
    STOP("중단"),
    END("종료");
    private String title;
    MissionStatusType(String title){
        this.title = title;
    }
}
