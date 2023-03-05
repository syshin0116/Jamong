package com.ss.Jamong.user.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

//사용자 권한
@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST"), USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), DEMO("ROLE_DEMO");

    private final String key;
}