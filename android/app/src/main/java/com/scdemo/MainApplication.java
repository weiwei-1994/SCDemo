package com.scdemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.masteratul.exceptionhandler.NativeExceptionHandlerIfc;
import com.masteratul.exceptionhandler.ReactNativeExceptionHandlerModule;
import com.masteratul.exceptionhandler.ReactNativeExceptionHandlerPackage;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;

public class MainApplication extends Application implements ReactApplication{

  private final ReactNativeHost reactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport ( ) {
      return false;
    }

    @Override
    protected List <ReactPackage> getPackages ( ) {
//      List<ReactPackage> packages = new PackageList(this).getPackages();
//      packages.add(new MyPackages());
                return Arrays.<ReactPackage>asList(
                        new MainReactPackage(),
                        new MyPackages(),
                        new ReactNativeExceptionHandlerPackage()
                );
//      return packages;
    }

    @Nullable
    @Override
    protected String getBundleAssetName ( ) {
      return "basic.jsbundle";
    }

    @Nullable
    @Override
    protected String getJSBundleFile() {
      Log.d("yj","files----"+getApplicationContext().getFilesDir().getAbsolutePath() + "/bundle" +"/basices.bundle");
      return getApplicationContext().getFilesDir().getAbsolutePath()+"/bundle/basices.jsbundle";
    }

    @Override
    protected String getJSMainModuleName ( ) {
      return "index";
    }
  };

  private static Context context;
  @Override
  public void onCreate() {
    super.onCreate();
    // If you opted-in for the New Architecture, we enable the TurboModule system
    //ReactFeatureFlags.useTurboModules = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
    SoLoader.init(this, /* native exopackage */ false);
    context = getApplicationContext();
    ReactNativeExceptionHandlerModule.setNativeExceptionHandler(new NativeExceptionHandlerIfc() {
      @Override
      public void handleNativeException(Thread thread, Throwable throwable, Thread.UncaughtExceptionHandler originalHandler) {
        // Put your error handling code here
        Log.d("yj","exception----e--thread id--"+thread.getId()+":::"+thread.getName());
        Log.d("yj","exception----e--throwable--"+throwable.getMessage());
        originalHandler.uncaughtException(thread,throwable);
      }
    });//This will override the default behaviour of displaying the recover activity.
  }

  /**
   * 获取全局上下文*/
  public static Context getContext() {
    return context;
  }

  @Override
  public ReactNativeHost getReactNativeHost ( ) {
    return reactNativeHost;
  }
}
