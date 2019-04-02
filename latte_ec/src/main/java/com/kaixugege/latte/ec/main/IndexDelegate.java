package com.kaixugege.latte.ec.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.kaixugege.latte_core.delegates.BaseDelegate;
import com.kaixugege.latte_core.net.RestCreator;
import com.kaixugege.latte_core.net.rx.RxRestClient;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.WeakHashMap;

/**
 * @Author: KaixuGege
 * Time:           2019/3/25
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class IndexDelegate extends BaseDelegate {

    //TODO:测试方法，没啥用
    void onCallRxGet() {
        final String url = "index.php";
        final WeakHashMap<String, Object> params = new WeakHashMap<>();

        final Observable<String> observable = RestCreator.getRxRestService().get(url, params);
        observable.subscribeOn(
                Schedulers.io()
        ).observeOn(
                AndroidSchedulers.mainThread()
        ).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                //请求开始
            }

            @Override
            public void onNext(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();//如果不在ui线程，这个会报错
            }

            @Override
            public void onError(Throwable e) {
                //请求错误
            }

            @Override
            public void onComplete() {
                //请求结束

            }
        });

    }

    //TODO:测试方法，没啥用
    void onCallRxRestClient() {
        final String url = "index.php";
        RxRestClient.builder().url(url).build().get().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();//如果不在ui线程，这个会报错
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }

        });
    }

    @Override
    public Object setLayout() {
        return null;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
