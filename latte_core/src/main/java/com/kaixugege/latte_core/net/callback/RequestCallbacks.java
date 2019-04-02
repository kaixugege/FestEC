package com.kaixugege.latte_core.net.callback;

import android.os.Handler;
import android.util.Log;
import com.kaixugege.latte_core.ui.loader.LatteLoader;
import com.kaixugege.latte_core.ui.loader.LoaderStyle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author: KaixuGege
 * Time:           2019/2/18
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 *
 * 常识:
 *      Handler 尽量声明成static类型,避免内存泄露
 */
public class RequestCallbacks implements Callback {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = new Handler() ;

    public RequestCallbacks(IRequest request, ISuccess success, IError error, IFailure failure, LoaderStyle style) {
        REQUEST = request;
        SUCCESS = success;
        ERROR = error;
        FAILURE = failure;
        LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call call, Response response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body().toString());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        if (LOADER_STYLE != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },5000);
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.d("HAHAHA","-------------------------------------------------------");
        Log.d("HAHAHA","retrofit failed "+t.getMessage());
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
    }
}
