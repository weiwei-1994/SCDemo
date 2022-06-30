package com.scdemo;

import android.app.Application;

import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

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
      List<ReactPackage> packages = new PackageList(this).getPackages();
      packages.add(new MyPackages());
//                return Arrays.<ReactPackage>asList(
//                        new MainReactPackage()
//                );
      return packages;
    }

    @Nullable
    @Override
    protected String getBundleAssetName ( ) {
      return "basic.jsbundle";
    }

    @Override
    protected String getJSMainModuleName ( ) {
      return "index";
    }
  };

  @Override
  public void onCreate() {
    super.onCreate();
    // If you opted-in for the New Architecture, we enable the TurboModule system
    //ReactFeatureFlags.useTurboModules = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
    SoLoader.init(this, /* native exopackage */ false);

  }


  @Override
  public ReactNativeHost getReactNativeHost ( ) {
    return reactNativeHost;
  }
}
