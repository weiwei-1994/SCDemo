package com.scdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;

public class RnActivity extends ReactActivity {
    public static String bundleName;

    private FinishActivityReceiver mReceiver;
    private String simpleName = "finishApp";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReceiver = new FinishActivityReceiver();
        registerFinishReceiver();
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

    private void registerFinishReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(simpleName);
        registerReceiver(mReceiver, intentFilter);
    }

    private class FinishActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //需要关闭页面的action
            if (simpleName.equals(intent.getAction())) {
                RnActivity.this.finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mReceiver!=null) {
            unregisterReceiver(mReceiver);
        }
    }
}
