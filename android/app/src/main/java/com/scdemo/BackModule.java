package com.scdemo;

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
        RnActivity.rnActivity.finish();
    }
}
