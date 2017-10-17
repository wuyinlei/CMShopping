package com.ruolan.cmshopping;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.ruolan.cainiao_core.activities.ProxyActivity;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_ec.delegate.launcher.LauncherDelegate;
import com.ruolan.cainiao_ec.delegate.launcher.LauncherScrollDelegate;
import com.ruolan.cainiao_ec.delegate.main.EcBottomDelegate;
import com.ruolan.cainiao_ec.delegate.sign.SignInDelegate;
import com.ruolan.cainiao_ec.delegate.sign.SignUpDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public CainiaoDelegate setRootDelegate() {
        return new SignUpDelegate();
    }


}
