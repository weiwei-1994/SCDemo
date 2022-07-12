package com.scdemo;

import android.app.Activity;
import android.os.Bundle;

import com.facebook.react.PackageList;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.soloader.SoLoader;

import java.util.List;

public class JsActivity extends Activity implements DefaultHardwareBackBtnHandler {

    private ReactRootView mReactRootView;

    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SoLoader.init(this, false);
        mReactRootView = new ReactRootView(this);
        //有一些第三方可能不能自动链接，对于这些包我们可以用下面的方式手动添加进来：
        //packages.add(new MyReactNativePackage());
        //同时需要手动把它们添加到'settings.gradle'和 'app/build.gradle'配置文件中
        List<ReactPackage> packages = new PackageList(getApplication()).getPackages();
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setCurrentActivity(this)
                .setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("index")
                .addPackages(packages)
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        //注意这里的MyReactNativeApp 必须对应"index.js"中的
        //"AppRegistry.registerComponent()的第一个参数
        mReactRootView.startReactApplication(mReactInstanceManager,"SCDemo",null);
        setContentView(mReactRootView);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
       // super.onBackPressed();
    }

//    @Override
//    public void onBackPressed() {
//        if (mReactInstanceManager != null) {
//            mReactInstanceManager.onBackPressed();
//        } else {
//            super.onBackPressed();
//        }
//    }
}
