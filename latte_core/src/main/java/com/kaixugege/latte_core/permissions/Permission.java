package com.kaixugege.latte_core.permissions;

/**
 * @Author: KaixuGege
 * Time:           2019/4/8
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class Permission {
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Permission(String permission) {
        this.permission = permission;
    }

    private String permission;

}
