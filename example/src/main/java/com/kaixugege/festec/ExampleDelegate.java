package com.kaixugege.festec;

import android.os.Bundle;
import android.view.View;

import android.widget.Toast;
import androidx.loader.content.Loader;
import com.kaixugege.latte_core.delegates.LatteDelegate;
import com.kaixugege.latte_core.net.RestClient;
import com.kaixugege.latte_core.net.callback.IError;
import com.kaixugege.latte_core.net.callback.IFailure;
import com.kaixugege.latte_core.net.callback.ISuccess;
import com.kaixugege.latte_core.ui.LoaderStyle;

/**
 * @Author: KaixuGege
 * Time:           2019/1/30
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("https://www.baidu.com/")
                .loader(getContext(), LoaderStyle.PacmanIndicator)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        Toast.makeText(getContext(), (String) response, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(
                        new IFailure() {
                            @Override
                            public void onFailure() {

                            }
                        }

                )
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
