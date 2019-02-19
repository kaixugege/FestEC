package com.kaixugege.latte_core.net.callback;

/**
 * @Author: KaixuGege
 * Time:           2019/1/31
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public interface IRequest {

    void onRequetStart();
    void onRequestStop();
    void onRequestEnd();
}
