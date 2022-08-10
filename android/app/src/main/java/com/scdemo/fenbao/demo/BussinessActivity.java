package com.scdemo.fenbao.demo;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.scdemo.Constance;
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
}