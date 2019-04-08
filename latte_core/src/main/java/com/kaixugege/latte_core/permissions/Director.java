package com.kaixugege.latte_core.permissions;

import com.kaixugege.latte_core.net.callback.IFailure;

/**
 * @Author: KaixuGege
 * Time:           2019/4/8
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class Director {

    public static Director setBuilderAbs(BuilderAbs builderAbs) {
        builderAbs = builderAbs;
        return DirectorHelper.getInstance();
    }

    private static BuilderAbs builderAbs = null;


    private Director() {

    }

    private static Director director() {
        return DirectorHelper.getInstance();
    }


    public static PermissionS build() {
        return builderAbs.create();
    }

    public static class DirectorHelper {

        public static Director getInstance() {
            return new Director();
        }

    }
}
