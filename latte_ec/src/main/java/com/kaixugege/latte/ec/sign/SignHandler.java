package com.kaixugege.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kaixugege.latte.ec.database.DatabaseManager;
import com.kaixugege.latte.ec.database.UserProfile;

import org.greenrobot.greendao.database.Database;

/**
 * @Author: KaixuGege
 * Time:           2019/4/3
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class SignHandler {

    public static void onSignUp(String response) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(profile);
    }

}
