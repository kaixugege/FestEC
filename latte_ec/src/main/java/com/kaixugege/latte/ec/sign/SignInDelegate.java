package com.kaixugege.latte.ec.sign;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.joanzapata.iconify.widget.IconTextView;
import com.kaixugege.latte.ec.R;
import com.kaixugege.latte_core.app.ISignListener;
import com.kaixugege.latte_core.delegates.LatteDelegate;
import com.kaixugege.latte_core.net.RestClient;
import com.kaixugege.latte_core.net.callback.IError;
import com.kaixugege.latte_core.net.callback.IFailure;
import com.kaixugege.latte_core.net.callback.ISuccess;
import com.kaixugege.latte_core.permissions.Builder;
import com.kaixugege.latte_core.permissions.Director;
import com.kaixugege.latte_core.permissions.Per;
import com.kaixugege.latte_core.permissions.PerBuilder;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @Author: KaixuGege
 * Time:           2019/4/2
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class SignInDelegate extends LatteDelegate implements View.OnClickListener {
    private TextInputEditText mEmail;
    private TextInputEditText mPassword;
    private AppCompatButton btn_sign_in;
    private AppCompatTextView tv_link_sign_up;
    private IconTextView loginWechat;


    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // activity是否实现了这个接口
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        mEmail = rootView.findViewById(R.id.edit_sign_in_email);
        mPassword = rootView.findViewById(R.id.edit_sign_in_password);
        btn_sign_in = rootView.findViewById(R.id.btn_sign_in_sign);
        tv_link_sign_up = rootView.findViewById(R.id.tv_link_sign_up);
        loginWechat = rootView.findViewById(R.id.icon_sign_in_wechat);
        loginWechat.setOnClickListener(this);
        tv_link_sign_up.setOnClickListener(this);
        btn_sign_in.setOnClickListener(this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            Director director = new Director(new PerBuilder())
                    .addPer(new Per(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    .addPer(new Per(Manifest.permission.ACCESS_NETWORK_STATE))
                    .addPer(new Per(Manifest.permission.CHANGE_WIFI_STATE))
                    .addPer(new Per(Manifest.permission.ACCESS_WIFI_STATE))
                    .addPer(new Per(Manifest.permission.READ_PHONE_STATE))
                    .addPer(new Per(Manifest.permission.ACCESS_FINE_LOCATION))
                    .addPer(new Per(Manifest.permission.ACCESS_COARSE_LOCATION))

                    .addPer(new Per(Manifest.permission.SYSTEM_ALERT_WINDOW))
                    .addPer(new Per(Manifest.permission.INTERNET))//网络请求
                    .addPer(new Per(Manifest.permission.CALL_PHONE))
                    .addPer(new Per(Manifest.permission.CAMERA))
                    .checkPermission(this.getContext())
                    .startRequestPermission(getActivity());

        }

    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                RestClient.builder()
                        .url("http://192.168.10.23:80/RestDataServer/api/user_profile")
//                        .url("https://www.baidu.com/")
                        .params("email", mEmail.getText().toString())
                        .params("password", mPassword.getText().toString())
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
//                                LatteLog.d("USER_PROFILE", "返回内容"+response);
                                Log.d("USER_PROFILE", "" + response);
                                System.out.println("  收到回复消息" + response);
                                SignHandler.onSignIn(response, mISignListener);
                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure() {
//                                LatteLog.e("USER_PROFILE", "失败了。。");
                                Log.d("USER_PROFILE", "失败啦。。");
                            }
                        })
                        .error(new IError() {
                            @Override

                            public void onError(int code, String msg) {
                                Log.d("USER_PROFILE", "异常。。" + code + "  " + msg.toString());
//                                LatteLog.e("USER_PROFILE", "异常。。" + msg.toString());

                            }
                        })
                        .build()
                        .get();
                Log.d("USER_PROFILE", "请求数据了");
                Toast.makeText(getContext(), "通过验证", Toast.LENGTH_SHORT).show();
            }
        }
    };


    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;


        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isPass = false;
            mEmail.setError("错误的邮件格式");
        } else {
            mEmail.setError(null);
        }


        if (password.isEmpty() || password.length() < 6) {
            isPass = false;
            mPassword.setError("请填写至少6位数");
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btn_sign_in.getId()) {

            if (checkForm()) {
//                RestClient.builder().url("sign_up").params("", "").success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//
//                    }
//                }).build().post();

                handler.sendEmptyMessage(1);
                Toast.makeText(getContext(), "通过验证", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == tv_link_sign_up.getId()) {
            Toast.makeText(getContext(), "注册", Toast.LENGTH_SHORT).show();
            start(new SignUpDelegate());
        }
    }
}
