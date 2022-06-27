package com.scdemo;

import android.app.Application;
import android.content.Context;

import com.facebook.soloader.SoLoader;

public class MainApplication extends Application {

  private static Context context;
  @Override
  public void onCreate() {
    super.onCreate();
    // If you opted-in for the New Architecture, we enable the TurboModule system
    //ReactFeatureFlags.useTurboModules = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
    SoLoader.init(this, /* native exopackage */ false);
    context = getApplicationContext();
  }

  /**
   * 获取全局上下文*/
  public static Context getContext() {
    return context;
  }


}
