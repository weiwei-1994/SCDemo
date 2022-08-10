package com.scdemo.fenbao.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.masteratul.exceptionhandler.ReactNativeExceptionHandlerModule;
import com.scdemo.Constance;
import com.scdemo.RnActivity;
import com.scdemo.RnBundle;
import com.scdemo.fenbao.AsyncReactActivity;

public class BussinessActivity extends AsyncReactActivity {
    private String simpleName = "finishApp";
    private FinishActivityReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReceiver = new FinishActivityReceiver();

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerFinishReceiver();
    }

    @Nullable
    @Override
    protected String getMainComponentName ( ) {
        return "SCDemo";
    }

    @Override
    protected RnBundle getBundle ( ) {
        RnBundle bundle = new RnBundle();
        switch (Constance.SCRIPTTYPE) {
            case "asset":
                bundle.scriptType = ScriptType.ASSET;
                bundle.scriptPath = "scdemo-business.jsbundle";
                bundle.scriptUrl = "scdemo-business.jsbundle";
                break;
            case "file":
                bundle.scriptType = ScriptType.FILE;
                bundle.scriptPath = "/bundle/businesses.jsbundle";
                bundle.scriptUrl = "/bundle/businesses.jsbundle";
                break;
        }

        return bundle;
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
                BussinessActivity.this.finish();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mReceiver!=null) {
            unregisterReceiver(mReceiver);
        }
    }
}