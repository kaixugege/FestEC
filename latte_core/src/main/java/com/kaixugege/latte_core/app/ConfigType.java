package com.kaixugege.latte_core.app;

/**
 * @Author: KaixuGege
 * Time:           2019/1/3
 * ProjectName:    FestEC
 * ClassName:
 * Info:枚举类在整个应用程序时唯一的单例，并且只能被初始化一次
 * @param: CONFIG_READY
 */
public enum ConfigType {
    API_HOST,
    APPLICATION_CONTEXT,
    CONFIG_READY,//是否初始化完成
    ICON
}
