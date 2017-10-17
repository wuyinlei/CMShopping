package com.ruolan.cainiao_ec.delegate.sign;

import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_core.net.RestClient;
import com.ruolan.cainiao_core.net.callback.IError;
import com.ruolan.cainiao_core.net.callback.IFailure;
import com.ruolan.cainiao_core.net.callback.ISuccess;
import com.ruolan.cainiao_core.net.rx.RxRestClient;
import com.ruolan.cainiao_core.ui.CainiaoLoader;
import com.ruolan.cainiao_core.util.log.CainiaoLogger;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.R2;
import com.ruolan.cainiao_ec.delegate.main.EcBottomDelegate;
import com.ruolan.cainiao_ec.delegate.main.index.IndexDelegate;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.SafeObserver;

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
        if (checkForm())

        RestClient.builder()
                .params("phone",mPhone.getText().toString().trim())
                .params("password",mPassword.getText().toString().trim())
                .url("http://easy-mock.com/mock/59e57875f757730a12fd0752/test/user_profile")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        CainiaoLogger.d("SignInDelegate",response);
                        SignHandler.onSignIn(response);
                        getSupportDelegate().start(new EcBottomDelegate());
                    }
                })
        .error(new IError() {
            @Override
            public void onError(int code, String msg) {
                Log.d("SignInDelegate", "code:" + code);
            }
        }).failure(new IFailure() {
            @Override
            public void onFailure() {
                Log.d("SignInDelegate", "失败了");
            }
        })
                .build()
                .post();
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

        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()) {
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
