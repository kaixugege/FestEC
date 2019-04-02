package com.kaixugege.latte_core.net.rx;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * @Author: KaixuGege
 * Time:           2019/3/25
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public interface RxRestService {


    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String, Object> params);

    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, Object> params);

    @POST
    Observable<String> postRaw(@Url String url, @Body RequestBody body);


    @FormUrlEncoded
    @POST
    Observable<String> put(@Url String url, @FieldMap Map<String, Object> params);

    @POST
    Observable<String> putRaw(@Url String url,@Body RequestBody body);


    @DELETE
    Observable<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    @Streaming //边下载边写入,不使用Streaming会直接下载到内存中去
    @GET
    Observable<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);//默认模式是一次性下载到内存里，下载完成后再统一写入文件， 文件过大时容易内存溢出

    @Multipart
    @POST
    Observable<String> upload(@Url String url, @Part MultipartBody.Part file);
}
