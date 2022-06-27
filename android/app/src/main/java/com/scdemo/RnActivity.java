package com.scdemo;

import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;

public class RnActivity extends ReactActivity {
    public static String bundleName;

    public static RnActivity rnActivity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rnActivity = this;
    }

    @Override
    protected String getMainComponentName() {
        return "SCDemo";
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {

        DispatchDelegate delegate = new DispatchDelegate(this, bundleName);

        return delegate;
    }
}
