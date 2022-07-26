package com.scdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.masteratul.exceptionhandler.NativeExceptionHandlerIfc;
import com.masteratul.exceptionhandler.ReactNativeExceptionHandlerModule;

public class RnActivity extends ReactActivity {
    public static String bundleName;

    private FinishActivityReceiver mReceiver;
    private String simpleName = "finishApp";
    private String reStartName = "restartname";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReceiver = new FinishActivityReceiver();

        //ReactNativeExceptionHandlerModule.replaceErrorScreenActivityClass(RnActivity.class); //This will replace the native error handler popup with your own custom activity.

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerFinishReceiver();
    }

    @Override
    protected String getMainComponentName() {
        return "SCDemo";
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        DispatchDelegate delegate;
            try {
                delegate = new DispatchDelegate(this, bundleName);
            }catch (Exception e){
                delegate = null;
                Log.d("yangjie","exception------aaa---poweronException:::"+e.getMessage());
            }

            return delegate;

    }

    private void registerFinishReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(simpleName);
        intentFilter.addAction(reStartName);
        registerReceiver(mReceiver, intentFilter);
    }

    private class FinishActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //需要关闭页面的action
            if (simpleName.equals(intent.getAction())) {
                RnActivity.this.finish();
            }
            if(reStartName.equals(intent.getAction())) {
                reStartActivity();
            }
        }
    }

    private void reStartActivity() {
        Toast.makeText(RnActivity.this,"新版本有问题，现已回滚上个正常版本，请知晓",Toast.LENGTH_LONG).show();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mReceiver!=null) {
            unregisterReceiver(mReceiver);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
