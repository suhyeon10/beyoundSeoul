package com.youngsquad.travel.domain.model;

public enum TeamMemberRole {

    READER("reader"),
    MEMBER("member");

    private String role;
    TeamMemberRole(String role){
        this.role = role;
    }
}
