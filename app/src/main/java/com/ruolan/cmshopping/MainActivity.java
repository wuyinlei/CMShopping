package com.ruolan.cmshopping;

import com.ruolan.cainiao_core.activities.ProxyActivity;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_ec.delegate.launcher.LauncherDelegate;
import com.ruolan.cainiao_ec.delegate.main.EcBottomDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public CainiaoDelegate setRootDelegate() {
        return new LauncherDelegate();
    }


}
