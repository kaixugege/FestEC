package com.kaixugege.latte_core.net.rx;

import android.content.Context;
import com.kaixugege.latte_core.net.RestCreator;
import com.kaixugege.latte_core.ui.loader.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @Author: KaixuGege
 * Time:           2019/1/31
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class RxRestClientBuilder {


    public static final Map<String, Object> mPARAMS = RestCreator.getParans();
    private String mUrl = null;
    private RequestBody mBODY = null;
    private Context mContext = null;
    private File mFile = null;
    private LoaderStyle mLoaderStyle = null;

    RxRestClientBuilder() {

    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        this.mPARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {

        this.mPARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        this.mBODY = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
        return this;
    }


    private Map<String, Object> checkParams() {
        if (mPARAMS == null) {
            return new WeakHashMap<>();
        }
        return mPARAMS;
    }


    public final RxRestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClient build() {
        return new RxRestClient(mUrl, mPARAMS, mBODY, mFile, mContext, mLoaderStyle);
    }
}
