package com.scdemo;

import android.app.Application;

import com.facebook.soloader.SoLoader;

public class MainApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    // If you opted-in for the New Architecture, we enable the TurboModule system
    //ReactFeatureFlags.useTurboModules = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
    SoLoader.init(this, /* native exopackage */ false);
  }


}
