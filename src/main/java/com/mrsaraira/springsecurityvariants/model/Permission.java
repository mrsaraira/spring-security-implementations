package com.mrsaraira.springsecurityvariants.model;

import lombok.Getter;

public enum Permission {
    DEVELOPERS_READ("Read"),
    DEVELOPERS_WRITE("Write");

    @Getter
    private final String permissionName;


    Permission(String permissionName) {
        this.permissionName = permissionName;
    }
}
