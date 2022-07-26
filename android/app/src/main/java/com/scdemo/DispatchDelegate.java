package com.scdemo;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import com.masteratul.exceptionhandler.ReactNativeExceptionHandlerPackage;
import com.microsoft.codepush.react.CodePush;
import com.scdemo.utils.RollBack;

import java.util.Arrays;
import java.util.List;

//DispatchDelegate
public class DispatchDelegate extends ReactActivityDelegate {

    private Activity activity;
    private String bundleName;
    private String reStartName = "restartname";
    private  ReactInstanceManagerBuilder builder;


    public DispatchDelegate(Activity activity, @Nullable String bundleName) {
        super(activity, bundleName);
        this.activity = activity;
        this.bundleName = bundleName;
    }

    @Override
    protected ReactNativeHost getReactNativeHost() {

        ReactNativeHost mReactNativeHost = new ReactNativeHost(activity.getApplication()) {
            @Override
            public boolean getUseDeveloperSupport() {
                return BuildConfig.DEBUG;
            }

            @Override
            protected ReactInstanceManager createReactInstanceManager() {
                builder = ReactInstanceManager.builder()
                        .setApplication(this.getApplication())
                        .setJSMainModulePath(getJSMainModuleName())
                        .setUseDeveloperSupport(getUseDeveloperSupport())
                        .setRedBoxHandler(getRedBoxHandler())
                        .setUIImplementationProvider(getUIImplementationProvider())
                        .setInitialLifecycleState(LifecycleState.BEFORE_CREATE)
                        .setNativeModuleCallExceptionHandler(new NativeModuleCallExceptionHandler(){

                            @Override
                            public void handleException(Exception e) {
                                Log.d("yangjie","exception------aaa---create::"+e.getMessage());
                                if(e.getMessage().contains("AppRegistry.runApplication")) {
                                    RollBack.setRollBack();
                                    Intent in = new Intent();
                                    in.setAction(reStartName);
                                    MainApplication.getContext().sendBroadcast(in);
                                }
                            }
                        });

                createReactInstanceManager().getCurrentReactContext();

                for (ReactPackage reactPackage : getPackages()) {
                    builder.addPackage(reactPackage);
                }

                String jsBundleFile = getJSBundleFile();
                if (jsBundleFile != null) {
                    builder.setJSBundleFile(jsBundleFile);
                } else {
                    builder.setBundleAssetName(Assertions.assertNotNull(getBundleAssetName()));
                }
                return builder.build();
            }

            //注册原生模块，这样
            @Override
            protected List<ReactPackage> getPackages() {
//                List<ReactPackage> packages = new PackageList(this).getPackages();
//                packages.add(new MyPackages());
                return Arrays.<ReactPackage>asList(
                        new MyPackages(),
                        new MainReactPackage(),
                        new CodePush(BuildConfig.CODEPUSH_KEY,MainApplication.getContext(),BuildConfig.DEBUG),
                        new ReactNativeExceptionHandlerPackage()
                );
//                return packages;
            }

            @Nullable
            @Override
            protected String getJSBundleFile() {
                //return super.getJSBundleFile();
                return CodePush.getJSBundleFile();
            }

            @Nullable
            @Override
            protected String getBundleAssetName() {
                return "index.android.bundle";
            }

            @Override
            protected String getJSMainModuleName() {
                return "index";
            }


        };
        return mReactNativeHost;
    }
}