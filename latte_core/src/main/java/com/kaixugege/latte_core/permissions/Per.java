package com.kaixugege.latte_core.permissions;

/**
 * @Author: KaixuGege
 * Time:           2019/4/9
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class Per {
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }


    public Per(String permission) {
        this.permission = permission;
    }

    private String permission;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    private boolean state;





}
