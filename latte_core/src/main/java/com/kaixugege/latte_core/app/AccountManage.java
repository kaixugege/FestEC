package com.kaixugege.latte_core.app;

import com.kaixugege.latte_core.util.Storage.LattePreference;

/**
 * @Author: KaixuGege
 * Time:           2019/4/10
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class AccountManage {
    private enum SignTag {
        SIGN_TAG
    }


    //保存用户登陆状态，在登陆之后调用
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn())
            checker.onSignIn();
        else
            checker.onNotSignIn();
    }

}
