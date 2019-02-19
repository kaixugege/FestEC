package com.kaixugege.latte_core.net;

import android.os.StatFs;
import com.kaixugege.latte_core.app.ConfigType;
import com.kaixugege.latte_core.app.Latte;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: KaixuGege
 * Time:           2019/1/31
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class RestCreator {

    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }
    //这里是惰性加载
    public static WeakHashMap<String, Object> getParans() {
        return ParamsHolder.PARAMS;
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }


    private static final class RetrofitHodle {
        private static final String BASE_URL = (String) Latte.getConfigurations()
                .get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)//对 okhtttp 进行的惰性初始化
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient().newBuilder()

//                .addInterceptor()  //拦截器
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHodle.RETROFIT_CLIENT.create(RestService.class);
    }

}



