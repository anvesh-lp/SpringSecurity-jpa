package com.anvesh.springsecurityjpa.roles;

public enum UserPermissions {
    READ("read:user"),
    MODIFY("modify:user"),
    DELETE("delete:user"),
    ADD("add:user");

    private String value;

    UserPermissions(String permission) {
        this.value = permission;
    }
}
