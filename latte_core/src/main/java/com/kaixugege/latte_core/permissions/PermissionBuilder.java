package com.kaixugege.latte_core.permissions;

/**
 * @Author: KaixuGege
 * Time:           2019/4/8
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class PermissionBuilder extends BuilderAbs {

    public PermissionS getPermissionS() {
        return permissionS;
    }

    private PermissionBuilder(){
        permissionS = new PermissionS();
    }

    private PermissionS permissionS;

    public PermissionBuilder addPer(Permission permission){
        permissionS.permissions.add(permission);
        return this;
    }

    public PermissionBuilder delPer(Permission permission){
        permissionS.permissions.remove(permission);
        return this;
    }



    @Override
    PermissionS create() {
        return permissionS;
    }
}
