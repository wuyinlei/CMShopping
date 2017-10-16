package com.ruolan.cainiao_ec.delegate.sign;

import android.support.design.widget.TextInputEditText;
import android.util.Patterns;

import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wuyinlei on 2017/10/16.
 *
 * @function 登录界面
 */

public class SignInDelegate extends CainiaoDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }


    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {

        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat() {

    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    private boolean checkForm() {
        final String phone = mPhone.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();

        boolean isPass = true;

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


        return isPass;

    }
}
