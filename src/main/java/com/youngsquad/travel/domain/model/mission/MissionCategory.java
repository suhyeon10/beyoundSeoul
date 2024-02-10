package com.youngsquad.travel.domain.model.mission;

public enum MissionCategory {
    FOOD("먹거리"),
    TOUR("관광"),
    SOSO("소소");

    private String name;
    MissionCategory(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
