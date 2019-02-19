package com.kaixugege.latte_core.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * @Author: KaixuGege
 * Time:           2019/1/3
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */

public final class Latte {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static WeakHashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplication() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }


}
