package com.kaixugege.latte_core.permissions;

import android.content.Context;

/**
 * @Author: KaixuGege
 * Time:           2019/4/8
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class Example {
    private void main() {
        PermissionS permissionS = Director.setBuilderAbs(new PermissionBuilder()
                .addPer(new Permission("我是权限 a"))
                .addPer(new Permission("我是权限 b"))
                .addPer(new Permission("我是权限 c"))
        ).build();


            permissionS.permissions.toString();
    }
}
