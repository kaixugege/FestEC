package com.kaixugege.latte_core.net;

import android.content.Context;
import com.kaixugege.latte_core.net.callback.*;
import com.kaixugege.latte_core.net.download.DownloadHandler;
import com.kaixugege.latte_core.ui.loader.LatteLoader;
import com.kaixugege.latte_core.ui.loader.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

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
public class RestClient {

    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParans();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;//文件下载
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;
    private final File FILE;
    private LoaderStyle LOADER_STYLE;


    private Context CONTEXT;


    public RestClient(String mUrl,
                      Map<String, Object> params,
                      IRequest REQUEST,
                      ISuccess SUCCESS,
                      IError ERROR,
                      IFailure FAILURE,
                      RequestBody BODY,
                      File file,
                      Context context,
                      LoaderStyle loaderStyle,
                      String download_dir,
                      String extension,
                      String name) {
        this.URL = mUrl;
        this.PARAMS.putAll(params);
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
        this.BODY = BODY;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
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

            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;

            case POST:
                call = service.post(URL, PARAMS);
                break;

            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;

            case DELETE:
                call = service.delete(URL, PARAMS);
                break;

            case UPLOAD://上传
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, body);
                break;

            default:
                break;

        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }

    }

    @SuppressWarnings("unchecked")
    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(REQUEST, SUCCESS, ERROR, FAILURE, LOADER_STYLE);
    }


    // 具体使用方法
    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            System.out.println("post body="+BODY+" PARAMS="+PARAMS.toString()+" 开始post");
            request(HttpMethod.POST);
        } else {
            System.out.println("post body ！="+BODY+" 开始postRaw");
            if (PARAMS.isEmpty()) {
                throw new RuntimeException("params must be  null "+PARAMS.isEmpty());
            }
            request(HttpMethod.POST_RAW);
        }

    }

    public final void put() {

        if (BODY != null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void download() {
        new DownloadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME, SUCCESS, ERROR, FAILURE)
                .handleDownload();
    }
}
