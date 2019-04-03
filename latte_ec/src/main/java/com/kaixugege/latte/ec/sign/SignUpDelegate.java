package com.kaixugege.latte.ec.sign;

import android.media.MediaCodec;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kaixugege.latte.ec.R;
import com.kaixugege.latte_core.delegates.LatteDelegate;
import com.kaixugege.latte_core.net.RestClient;
import com.kaixugege.latte_core.net.callback.IFailure;
import com.kaixugege.latte_core.net.callback.ISuccess;
import com.kaixugege.latte_core.util.log.LatteLog;

import java.util.regex.Pattern;

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

    @Override
    public void onClick(View v) {
        if (v.getId() == btn_sign.getId()) {
            if (checkForm()) {
                RestClient.builder()
                        .url("http://192.168.99.179/RestDataServer/api/user_profile")
                        .params("name", mName.getText().toString())
                        .params("email", mEmail.getText().toString())
                        .params("phone", mPhoneNumber.getText().toString())
                        .params("password", mPassword.getText().toString() )
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                LatteLog.d("USER_PROFILE",response);
                                SignHandler.onSignUp(response);
                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure() {
                                LatteLog.d("USER_PROFILE","失败了。。");
                            }
                        })
                        .build()
                        .post();
                Toast.makeText(getContext(), "通过验证", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (v.getId() == mSignIn.getId()) {
                start(new SignInDelegate());
            }
        }
    }
}
