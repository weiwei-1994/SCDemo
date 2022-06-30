package com.scdemo.fenbao.demo;

import androidx.annotation.Nullable;

import com.scdemo.RnBundle;
import com.scdemo.fenbao.AsyncReactActivity;

public class BussinessActivity extends AsyncReactActivity {


    @Nullable
    @Override
    protected String getMainComponentName ( ) {
        return "SCDemo";
    }

    @Override
    protected RnBundle getBundle ( ) {
        RnBundle bundle = new RnBundle();
        bundle.scriptType = ScriptType.ASSET;
        bundle.scriptPath = "business.jsbundle";
        bundle.scriptUrl = "business.jsbundle";
        return bundle;
    }
}