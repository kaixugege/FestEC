package com.kaixugege.latte_core.net;

import android.content.Context;
import com.kaixugege.latte_core.net.callback.*;
import com.kaixugege.latte_core.ui.LatteLoader;
import com.kaixugege.latte_core.ui.LoaderStyle;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import java.util.Map;

/**
 * @Author: KaixuGege
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
public class RestClient {

    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParans();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;
    private LoaderStyle LOADER_STYLE;
    private Context CONTEXT;


    public RestClient(String mUrl,
                      Map<String, Object> params,
                      IRequest REQUEST,
                      ISuccess SUCCESS,
                      IError ERROR,
                      IFailure FAILURE,
                      RequestBody BODY, Context context, LoaderStyle loaderStyle) {
        this.URL = mUrl;
        this.PARAMS.putAll(params);
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
        this.BODY = BODY;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    // 建造者，以Builder 方式传递
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequetStart();
        }
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }

    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(REQUEST, SUCCESS, ERROR, FAILURE, LOADER_STYLE);
    }

    //
    //
    //
    // 具体使用方法
    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
