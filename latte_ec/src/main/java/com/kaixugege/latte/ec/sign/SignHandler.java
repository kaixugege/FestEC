package com.kaixugege.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kaixugege.latte.ec.database.DatabaseManager;
import com.kaixugege.latte.ec.database.UserProfile;
import com.kaixugege.latte_core.app.AccountManage;
import com.kaixugege.latte_core.app.ISignListener;

/**
 * @Author: KaixuGege
 * Time:           2019/4/3
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class SignHandler {

    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登陆成功了
        AccountManage.setSignState(true);
        signListener.onSignUpSuccess();
    }


    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登陆成功了
        AccountManage.setSignState(true);
        signListener.onSignInSuccess();
    }
}
