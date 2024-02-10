package com.youngsquad.travel.domain.model;

public enum TravelWithType {
    ALONE("혼자"),
    TOGETHER("여행 메이트와 함께");
    String detail;

    TravelWithType(String detail){
        this.detail = detail;
    }
    public static TravelWithType getByDetail(String detail) {
        for (TravelWithType type : values()) {
            if (type.detail.equals(detail)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No constant with detail: " + detail);
    }
}
