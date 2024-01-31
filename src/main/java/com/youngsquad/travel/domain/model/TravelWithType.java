package com.youngsquad.travel.domain.model;

public enum TravelWithType {
    ALONE("혼자"),
    TOGETHER("여행 메이트와 함께");
    private String name;

    TravelWithType(String name){
        this.name = name;
    }
}
