package com.ruolan.cmshopping;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.ruolan.cainiao_core.activities.ProxyActivity;
import com.ruolan.cainiao_core.app.Cainiao;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_core.ui.ILauncherListener;
import com.ruolan.cainiao_core.ui.OnLauncherFinishTag;
import com.ruolan.cainiao_ec.delegate.launcher.LauncherDelegate;
import com.ruolan.cainiao_ec.delegate.launcher.LauncherScrollDelegate;
import com.ruolan.cainiao_ec.delegate.main.EcBottomDelegate;
import com.ruolan.cainiao_ec.delegate.sign.ISignListener;
import com.ruolan.cainiao_ec.delegate.sign.SignInDelegate;
import com.ruolan.cainiao_ec.delegate.sign.SignUpDelegate;

public class MainActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Cainiao.getConfigurator().withActivity(this);
    }

    @Override
    public CainiaoDelegate setRootDelegate() {
        return new SignUpDelegate();
    }


    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        getSupportDelegate().startWithPop(new EcBottomDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        getSupportDelegate().start(new SignInDelegate());
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag finishTag) {
        if (finishTag == OnLauncherFinishTag.SIGINED) {
            //已经登录了 这个时候处理登录的逻辑
            getSupportDelegate().startWithPop(new EcBottomDelegate());
        } else if (finishTag == OnLauncherFinishTag.NOT_SIGNED) {
            //用户还没有登录  这个时候处理未登录的逻辑
            getSupportDelegate().startWithPop(new SignInDelegate());
        }
    }
}
