package com.kaixugege.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.kaixugege.latte.ec.R;
import com.kaixugege.latte_core.app.ISignListener;
import com.kaixugege.latte_core.delegates.LatteDelegate;
import com.kaixugege.latte_core.net.RestClient;
import com.kaixugege.latte_core.net.callback.IError;
import com.kaixugege.latte_core.net.callback.IFailure;
import com.kaixugege.latte_core.net.callback.ISuccess;


import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @Author: KaixuGege
 * Time:           2019/4/1
 * ProjectName:    FestEC
 * ClassName:
 * Info:
 */
public class SignUpDelegate extends LatteDelegate implements View.OnClickListener {
    private TextInputEditText mName;
    private TextInputEditText mEmail;
    private TextInputEditText mPhoneNumber;
    private TextInputEditText mPassword;
    private TextInputEditText mRePassword;
    private AppCompatButton btn_sign;
    private AppCompatTextView mSignIn;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }


    private ISignListener mISignListener = null;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // activity是否实现了这个接口
        if (activity instanceof  ISignListener){
            mISignListener = (ISignListener)activity;
        }
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        mName = rootView.findViewById(R.id.edit_sign_up_name);
        mEmail = rootView.findViewById(R.id.edit_sign_up_email);
        mPhoneNumber = rootView.findViewById(R.id.edit_sign_up_phone_number);
        mPassword = rootView.findViewById(R.id.edit_sign_up_password);
        mRePassword = rootView.findViewById(R.id.edit_sign_up_re_passowrd);
        btn_sign = rootView.findViewById(R.id.bt_sign_up_regist);
        mSignIn = rootView.findViewById(R.id.tv_link_sign_in);
        mSignIn.setOnClickListener(this);
        btn_sign.setOnClickListener(this);
    }

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhoneNumber.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            isPass = false;
            mName.setError("请输入姓名");
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isPass = false;
            mEmail.setError("错误的邮件格式");
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            isPass = false;
            mPhoneNumber.setError("手机号码错误");
        } else {
            mPhoneNumber.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            isPass = false;
            mPassword.setError("请填写至少6位数");
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty()) {
            isPass = false;
            mRePassword.setError("请输入姓名");
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                RestClient.builder()
                        .url("http://192.168.10.23:80/RestDataServer/api/user_profile")
//                        .url("https://www.baidu.com/")
                        .params("name", mName.getText().toString())
                        .params("email", mEmail.getText().toString())
                        .params("phone", mPhoneNumber.getText().toString())
                        .params("password", mPassword.getText().toString())
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
//                                LatteLog.d("USER_PROFILE", "返回内容"+response);
                                Log.d("USER_PROFILE",""+response);
                                System.out.println("  收到回复消息"+response);
                                SignHandler.onSignUp(response,mISignListener);
                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure() {
//                                LatteLog.e("USER_PROFILE", "失败了。。");
                                Log.d("USER_PROFILE", "失败啦。。" );
                            }
                        })
                        .error(new IError() {
                            @Override

                            public void onError(int code, String msg) {
                                Log.d("USER_PROFILE", "异常。。"+code+"  " + msg.toString());
//                                LatteLog.e("USER_PROFILE", "异常。。" + msg.toString());

                            }
                        })
                        .build()
                        .get();
                Log.d("USER_PROFILE","请求数据了");
                Toast.makeText(getContext(), "通过验证", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onClick(View v) {
        if (v.getId() == btn_sign.getId()) {
            if (checkForm()) {
                handler.sendEmptyMessage(1);
            }
        } else {
            if (v.getId() == mSignIn.getId()) {
                start(new SignInDelegate());
            }
        }
    }
}
