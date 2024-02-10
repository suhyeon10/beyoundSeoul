package com.youngsquad.travel.domain.model;

public enum TravelRoleType {
    MATE("메이트예요!"),
    READER("리더예요!");
    private String detail;
    TravelRoleType(String detail){
        this.detail = detail;
    }
    public String getDetail(){
        return detail;
    }
}
