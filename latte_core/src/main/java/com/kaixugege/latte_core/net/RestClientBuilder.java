package com.kaixugege.latte_core.net;

import android.content.Context;
import com.kaixugege.latte_core.net.callback.IError;
import com.kaixugege.latte_core.net.callback.IFailure;
import com.kaixugege.latte_core.net.callback.IRequest;
import com.kaixugege.latte_core.net.callback.ISuccess;
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
public class RestClientBuilder {


    public static final Map<String, Object> mPARAMS = RestCreator.getParans();
    private String mUrl = null;
    private IRequest mREQUEST = null;
    private ISuccess mSUCCESS = null;
    private IError mERROR = null;
    private IFailure mFAILURE = null;
    private RequestBody mBODY = null;
    private Context mContext = null;
    private File mFile = null;
    private LoaderStyle mLoaderStyle = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;


    public RestClientBuilder downloadDIr(String mDownloadDir) {
        this.mDownloadDir = mDownloadDir;
        return this;
    }

    public RestClientBuilder extension(String mExtension) {
        this.mExtension = mExtension;
        return this;
    }

    public RestClientBuilder name(String mName) {
        this.mName = mName;
        return this;
    }



    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        this.mPARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {

        this.mPARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBODY = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mSUCCESS = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mFAILURE = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError mERROR) {
        this.mERROR = mERROR;
        return this;
    }


    private Map<String, Object> checkParams() {
        if (mPARAMS == null) {
            return new WeakHashMap<>();
        }
        return mPARAMS;
    }


    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, mPARAMS, mREQUEST, mSUCCESS, mERROR, mFAILURE, mBODY, mFile, mContext, mLoaderStyle, mDownloadDir, mExtension, mName);
    }
}
