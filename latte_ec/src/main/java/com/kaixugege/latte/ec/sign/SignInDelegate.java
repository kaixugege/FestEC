package com.kaixugege.latte.ec.sign;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.joanzapata.iconify.widget.IconTextView;
import com.kaixugege.latte.ec.R;
import com.kaixugege.latte_core.delegates.LatteDelegate;

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
    }


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
                Toast.makeText(getContext(), "通过验证", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == tv_link_sign_up.getId()) {
            Toast.makeText(getContext(), "注册", Toast.LENGTH_SHORT).show();
            start(new SignUpDelegate());
        }
    }
}
