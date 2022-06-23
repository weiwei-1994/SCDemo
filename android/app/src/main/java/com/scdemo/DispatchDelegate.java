package com.scdemo;

import android.app.Activity;

import androidx.annotation.Nullable;

import com.facebook.react.PackageList;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;

import java.util.List;

//DispatchDelegate
public class DispatchDelegate extends ReactActivityDelegate {

    private Activity activity;
    private String bundleName;


    public DispatchDelegate(Activity activity, @Nullable String bundleName) {
        super(activity, bundleName);
        this.activity = activity;
        //this.bundleName = bundleName;
    }

    @Override
    protected ReactNativeHost getReactNativeHost() {

        ReactNativeHost mReactNativeHost = new ReactNativeHost(activity.getApplication()) {
            @Override
            public boolean getUseDeveloperSupport() {
                return BuildConfig.DEBUG;
            }
            //注册原生模块，这样
            @Override
            protected List<ReactPackage> getPackages() {
                List<ReactPackage> packages = new PackageList(this).getPackages();
                packages.add(new MyPackages());
//                return Arrays.<ReactPackage>asList(
//                        new MainReactPackage()
//                );
                return packages;
            }

            @Nullable
            @Override
            protected String getBundleAssetName() {
                return  "index.android.bundle";
            }

            @Override
            protected String getJSMainModuleName() {
                return "index";
            }
        };
        return mReactNativeHost;
    }
}