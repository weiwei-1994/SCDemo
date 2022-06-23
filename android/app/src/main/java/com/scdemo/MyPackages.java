package com.scdemo;

import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.scdemo.rectUI.ReactImageManager;
import com.scdemo.rectUI.ReactViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyPackages implements ReactPackage {
    @NonNull
    @Override
    public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new ToastModule(reactContext));
        modules.add(new ReactImageManager(reactContext));
        modules.add(new ReactViewManager(reactContext));
        return modules;
    }

    @NonNull
    @Override
    public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {
//        List<ViewManager> list = new ArrayList<>();
//        list.add(new ReactImageManager(reactContext));
//        list.add(new ReactViewManager(reactContext));
        return Arrays.asList(new ReactImageManager(reactContext),
                            new ReactViewManager(reactContext));
//        return list;
    }


}
