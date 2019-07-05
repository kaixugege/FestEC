package com.kaixugege.latte_core.net.Inrerceptors;

import okhttp3.*;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * @Author: KaixuGege
 * Time:           2019/3/14
 * ProjectName:    FestEC
 * ClassName:
 * Info:一个基础的拦截器
 */
public abstract class BaseInterceptor implements Interceptor {




    //获取有序排列的url参数段
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {

        final HttpUrl url = chain.request().url();
        int size = url.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }

    //通过key值获取value
    protected String getUrlParameters(Chain chain, String key) {
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }

    protected LinkedHashMap<String, String> getBodyparamesters(Chain chain) {
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        int size = formBody.size();
        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i), formBody.value(i));
        }
        return params;
    }

    protected String getBodyparamesters(Chain chain, String key) {
        return getBodyparamesters(chain).get(key);
    }
}
