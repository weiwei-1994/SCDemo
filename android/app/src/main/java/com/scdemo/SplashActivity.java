package com.scdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceEventListener;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;

public class SplashActivity extends AppCompatActivity implements ReactInstanceEventListener {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ReactInstanceManager reactInstanceManager = ((ReactApplication)getApplication()).getReactNativeHost().getReactInstanceManager();

        //监听加载结束的回调
        reactInstanceManager.addReactInstanceEventListener(this);

        if (!reactInstanceManager.hasStartedCreatingInitialContext()){
            reactInstanceManager.createReactContextInBackground();
        }
    }

    @Override
    public void onReactContextInitialized (ReactContext reactContext) {
        //当加载完成后启动首页
        startActivity(new Intent(SplashActivity.this,ListAcitvity.class));
        finish();
    }
}