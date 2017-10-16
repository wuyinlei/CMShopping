package com.ruolan.cainiao_ec.delegate.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.ruolan.cainiao_core.net.RestClient;
import com.ruolan.cainiao_core.net.callback.ISuccess;
import com.ruolan.cainiao_ec.R2;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_ec.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wuyinlei on 2017/10/16.
 *
 * @function 注册界面
 */

public class SignUpDelegate extends CainiaoDelegate {


    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder()
                    .url("sign_up")
                    .params("","")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {

                        }
                    }).build().post();
        }
    }


    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() {
        getSupportDelegate().start(new SignInDelegate());
    }


    private boolean checkForm() {
        final String name = mName.getText().toString().trim();
        final String email = mEmail.getText().toString().trim();
        final String phone = mPhone.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        final String rePassword = mRePassword.getText().toString().trim();

        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入用户名");
            isPass = false;
        } else {
            mName.setError(null);
        }


        if (email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || Patterns.PHONE.matcher(phone).matches()) {
            mPhone.setError("手机号错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || rePassword.equals(password)) {
            mRePassword.setError("两次输入的密码不一致,请检查");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;

    }
}
