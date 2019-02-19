package com.kaixugege.latte_core.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * @Author: KaixuGege
 * Time:           2019/1/30
 * ProjectName:    FestEC
 * ClassName:
 * Info:通用的封装
 */
public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST
    Call<String> put(@Url String url, @FieldMap Map<String, Object> params);

    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    @Streaming //边下载边写入
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);//默认模式是一次性下载到内存里，下载完成后再统一写入文件， 文件过大时容易内存溢出

    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part Map<String, Object> file);

}