package com.kaixugege.latte_core.app;

import okhttp3.Interceptor;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * @Author: KaixuGege
 * Time:           2019/1/3
 * ProjectName:    FestEC
 * ClassName:
 * Info:全局配置类
 * 1、懒汉模式初始化
 * 2、静态内部类单例模式初始化
 */
public class Configurator {

    //配置
    private static final WeakHashMap<Object, Object> LATTE_CONFIGS = new WeakHashMap<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();//okhttp 的拦截器

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);//这里默认这个配置文件为false
    }


    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public WeakHashMap<Object, Object> getLatteConfigs() {

        return LATTE_CONFIGS;

    }
    //静态内部类单例模式初始化
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }


    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    /**
     * 保证配置的完整性和安全性
     *
     * 获取配置得时候调用
     */
    private void checkConfiguratio(){
        final  boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw  new RuntimeException("Configuration is not ready, call configurre");//抛出一个运行时异常
        }
    }


    //这个注释是说这个类型并没有检测过，但是不抛出警告
    @SuppressWarnings("unchecked")
    public final <T>  T getConfiguration(Object key){
        checkConfiguratio();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }

}
