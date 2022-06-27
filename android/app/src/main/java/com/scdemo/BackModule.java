package com.scdemo;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

public class BackModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactApplicationContext;

    private static final String NATIVE_NAME = "NativeModule";

    private String simpleName = "finishApp";

    public BackModule(ReactApplicationContext context){
        super(context);
        reactApplicationContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return NATIVE_NAME;
    }


    @ReactMethod
    public void nativeStackPop(){
        Intent in = new Intent();
        in.setAction(simpleName);
        MainApplication.getContext().sendBroadcast(in);
    }
}
