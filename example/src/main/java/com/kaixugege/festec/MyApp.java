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
                .withApiHost("http://127.0.0.1")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();

        DatabaseManager.getInstance().init(this);
    }

}
