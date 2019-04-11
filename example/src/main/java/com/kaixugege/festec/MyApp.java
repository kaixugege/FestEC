package com.kaixugege.festec;

import android.app.Application;

import com.kaixugege.latte.ec.database.DatabaseManager;
import com.kaixugege.latte_core.app.Latte;
import com.kaixugege.latte_core.net.Inrerceptors.DebugInterceptor;

/**
 * @Author: KaixuGege
 * Time:           2019/1/3
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://192.168.10.23")
                .withInterceptor(new DebugInterceptor("/RestDataServer/api/user_profile", R.raw.signup))
//                .withInterceptor(new DebugInterceptor("index", R.raw.test))

                .configure();

        DatabaseManager.getInstance().init(this);
    }

}
