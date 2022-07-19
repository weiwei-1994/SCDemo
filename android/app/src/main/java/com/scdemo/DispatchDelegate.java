package com.scdemo;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import androidx.annotation.Nullable;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.common.SurfaceDelegate;
import com.facebook.react.devsupport.interfaces.BundleLoadCallback;
import com.facebook.react.devsupport.interfaces.DevOptionHandler;
import com.facebook.react.devsupport.interfaces.DevSplitBundleCallback;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.ErrorCustomizer;
import com.facebook.react.devsupport.interfaces.ErrorType;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.devsupport.interfaces.StackFrame;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.shell.MainReactPackage;
import com.masteratul.exceptionhandler.ReactNativeExceptionHandlerPackage;
import com.microsoft.codepush.react.CodePush;
import com.scdemo.utils.RollBack;

import java.io.File;
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
//                        .setNativeModuleCallExceptionHandler(new DevSupportManager() {
//                            @Override
//                            public void showNewJavaError(String s, Throwable throwable) {
//                                Log.d("yangjie","exception----dev--aaa---create::"+s);
//                            }
//
//                            @Override
//                            public void addCustomDevOption(String s, DevOptionHandler devOptionHandler) {
//                                Log.d("yangjie","exception----dev--bbb---create::"+s);
//                            }
//
//                            @Nullable
//                            @Override
//                            public View createRootView(String s) {
//                                return null;
//                            }
//
//                            @Override
//                            public void destroyRootView(View view) {
//
//                            }
//
//                            @Override
//                            public void showNewJSError(String s, ReadableArray readableArray, int i) {
//                                Log.d("yangjie","exception----dev--ccc---create::"+s);
//                            }
//
//                            @Override
//                            public void updateJSError(String s, ReadableArray readableArray, int i) {
//                                Log.d("yangjie","exception----dev--dddd---create::"+s);
//                            }
//
//                            @Override
//                            public void hideRedboxDialog() {
//
//                            }
//
//                            @Override
//                            public void showDevOptionsDialog() {
//
//                            }
//
//                            @Override
//                            public void setDevSupportEnabled(boolean b) {
//
//                            }
//
//                            @Override
//                            public void startInspector() {
//
//                            }
//
//                            @Override
//                            public void stopInspector() {
//
//                            }
//
//                            @Override
//                            public boolean getDevSupportEnabled() {
//                                return false;
//                            }
//
//                            @Override
//                            public DeveloperSettings getDevSettings() {
//                                return null;
//                            }
//
//                            @Override
//                            public RedBoxHandler getRedBoxHandler() {
//                                return null;
//                            }
//
//                            @Override
//                            public void onNewReactContextCreated(ReactContext reactContext) {
//
//                            }
//
//                            @Override
//                            public void onReactInstanceDestroyed(ReactContext reactContext) {
//
//                            }
//
//                            @Override
//                            public String getSourceMapUrl() {
//                                return null;
//                            }
//
//                            @Override
//                            public String getSourceUrl() {
//                                return null;
//                            }
//
//                            @Override
//                            public String getJSBundleURLForRemoteDebugging() {
//                                return null;
//                            }
//
//                            @Override
//                            public String getDownloadedJSBundleFile() {
//                                return null;
//                            }
//
//                            @Override
//                            public boolean hasUpToDateJSBundleInCache() {
//                                return false;
//                            }
//
//                            @Override
//                            public void reloadSettings() {
//
//                            }
//
//                            @Override
//                            public void handleReloadJS() {
//                                Log.d("yangjie","exception----dev--eeee---create::");
//                            }
//
//                            @Override
//                            public void reloadJSFromServer(String s) {
//
//                            }
//
//                            @Override
//                            public void reloadJSFromServer(String s, BundleLoadCallback bundleLoadCallback) {
//
//                            }
//
//                            @Override
//                            public void loadSplitBundleFromServer(String s, DevSplitBundleCallback devSplitBundleCallback) {
//
//                            }
//
//                            @Override
//                            public void isPackagerRunning(PackagerStatusCallback packagerStatusCallback) {
//
//                            }
//
//                            @Override
//                            public void setHotModuleReplacementEnabled(boolean b) {
//
//                            }
//
//                            @Override
//                            public void setRemoteJSDebugEnabled(boolean b) {
//
//                            }
//
//                            @Override
//                            public void setFpsDebugEnabled(boolean b) {
//
//                            }
//
//                            @Override
//                            public void toggleElementInspector() {
//
//                            }
//
//                            @Nullable
//                            @Override
//                            public File downloadBundleResourceFromUrlSync(String s, File file) {
//                                return null;
//                            }
//
//                            @Nullable
//                            @Override
//                            public String getLastErrorTitle() {
//                                Log.d("yangjie","exception----dev--fff---create::");
//                                return null;
//                            }
//
//                            @Nullable
//                            @Override
//                            public StackFrame[] getLastErrorStack() {
//                                Log.d("yangjie","exception----dev--ggg---create::"+(new StackFrame[0]));
//                                return new StackFrame[0];
//                            }
//
//                            @Nullable
//                            @Override
//                            public ErrorType getLastErrorType() {
//                                Log.d("yangjie","exception----dev--hhhh---create::");
//                                return null;
//                            }
//
//                            @Override
//                            public int getLastErrorCookie() {
//                                Log.d("yangjie","exception----dev--iiii---create::");
//                                return 0;
//                            }
//
//                            @Override
//                            public void registerErrorCustomizer(ErrorCustomizer errorCustomizer) {
//                                Log.d("yangjie","exception----dev--jjjj---create::");
//                            }
//
//                            @Override
//                            public Pair<String, StackFrame[]> processErrorCustomizers(Pair<String, StackFrame[]> pair) {
//                                return null;
//                            }
//
//                            @Override
//                            public void setPackagerLocationCustomizer(PackagerLocationCustomizer packagerLocationCustomizer) {
//
//                            }
//
//                            @Nullable
//                            @Override
//                            public Activity getCurrentActivity() {
//                                return null;
//                            }
//
//                            @Nullable
//                            @Override
//                            public SurfaceDelegate createSurfaceDelegate(String s) {
//                                return null;
//                            }
//
//                            @Override
//                            public void handleException(Exception e) {
//                                Log.d("yangjie","exception----dev--kkkk---create::"+e.getMessage());
//                            }
//                        });

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