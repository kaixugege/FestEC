package com.kaixugege.latte_core.net.rx;

import android.content.Context;
import com.kaixugege.latte_core.net.HttpMethod;
import com.kaixugege.latte_core.net.RestCreator;
import com.kaixugege.latte_core.ui.loader.LatteLoader;
import com.kaixugege.latte_core.ui.loader.LoaderStyle;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import java.io.File;
import java.util.Map;

/**
 * Author: KaixuGege
 * Time:           2019/1/30
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 * <p>
 * 建造者模式分为标准和安卓简化的
 * <p>
 * 把建造者和宿主类分割开
 * <p>
 * execute 在主线程中执行的
 * enqueue 会在起一个线程去执行
 */
public class RxRestClient {

    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParans();
    private final RequestBody BODY;
    private final File FILE;
    private LoaderStyle LOADER_STYLE;
    private Context CONTEXT;


    public RxRestClient(String mUrl,
                        Map<String, Object> params,
                        RequestBody BODY,
                        File file,
                        Context context,
                        LoaderStyle loaderStyle
                         ) {
        this.URL = mUrl;
        this.PARAMS.putAll(params);
        this.BODY = BODY;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;
    }

    // 建造者，以Builder 方式传递
    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;


        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {

            case GET:
                observable = service.get(URL, PARAMS);
                break;

            case PUT:
                observable = service.put(URL, PARAMS);
                break;

            case PUT_RAW:
                observable = service.put(URL, PARAMS);
                break;

            case POST:
                observable = service.post(URL, PARAMS);
                break;

            case POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;

            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;

            case UPLOAD://上传
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = RestCreator.getRxRestService().upload(URL, body);
                break;

            default:
                break;

        }
        return observable;

    }




    // 具体使用方法
    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post() {
        if (BODY != null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.POST_RAW);
        }

    }

    public final Observable<String> put() {

        if (BODY != null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download() {

        final Observable<ResponseBody> requestBodyObservable = RestCreator.getRxRestService().download(URL, PARAMS);
        return requestBodyObservable;
    }
}
