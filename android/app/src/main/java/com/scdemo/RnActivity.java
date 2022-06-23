package com.scdemo;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;

public class RnActivity extends ReactActivity {
    public static String bundleName;

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {

        DispatchDelegate delegate = new DispatchDelegate(this, bundleName);

        return delegate;
    }
}
