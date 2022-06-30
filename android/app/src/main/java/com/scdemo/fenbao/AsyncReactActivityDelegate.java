package com.scdemo.fenbao;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactNativeHost;
import com.scdemo.MainApplication;

import androidx.annotation.Nullable;

/**
 * Created by wangjian on 2022/6/27
 */
public class AsyncReactActivityDelegate extends ReactActivityDelegate {

    private Activity context;

    public AsyncReactActivityDelegate (Activity activity, @Nullable String mainComponentName) {
        super(activity, mainComponentName);
        this.context = activity;
    }

    public AsyncReactActivityDelegate (ReactActivity activity, @Nullable String mainComponentName) {
        super(activity, mainComponentName);
        this.context = activity;
    }

    @Override
    public ReactNativeHost getReactNativeHost ( ) {
        return super.getReactNativeHost();
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void loadApp (String appKey) {
        super.loadApp(appKey);
    }

    @Override
    public void onPause ( ) {
        super.onPause();
    }

    @Override
    public void onResume ( ) {
        super.onResume();
    }

    @Override
    public void onDestroy ( ) {
        super.onDestroy();
    }
}
