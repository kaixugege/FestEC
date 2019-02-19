package com.kaixugege.latte_core.net.callback;

import java.util.OptionalInt;

/**
 * @Author: KaixuGege
 * Time:           2019/1/31
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public interface IError {
    void onError(int code,String msg);
}
