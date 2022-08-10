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
import com.swmansion.gesturehandler.RNGestureHandlerPackage;
import com.swmansion.reanimated.ReanimatedPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;

public class MainApplication extends Application implements ReactApplication {

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
                        new MyPackages(),
                        new ReactNativeExceptionHandlerPackage(),
                        new MainReactPackage(),
                        new ReanimatedPackage(),
                        new RNGestureHandlerPackage(),
                        new RNScreensPackage(),
                        new SafeAreaContextPackage()
                );
//      return packages;
    }

    @Nullable
    @Override
    protected String getBundleAssetName ( ) {
      return "common.jsbundle";
    }

    @Nullable
    @Override
    protected String getJSBundleFile() {
      File file = new File(getApplicationContext().getFilesDir().getAbsolutePath()+"/bundle/common.jsbundle");
      if(file.exists()) {
        return getApplicationContext().getFilesDir().getAbsolutePath() + "/bundle/common.jsbundle";
      }else{
        return null;
      }
      //return "";
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
        Log.d("yj","handleNativeException-------thread---"+thread.getName());
        Log.d("yj","handleNativeException-------throwable---"+throwable.getMessage());
        //originalHandler.uncaughtException(thread,throwable);
      }
    });
    ReactNativeExceptionHandlerModule.replaceErrorScreenActivityClass(ReplaceActivity.class); //This will replace the native error handler popup with your own custom activity
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
