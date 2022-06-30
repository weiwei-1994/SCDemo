package com.scdemo.fenbao.util;


import com.facebook.react.bridge.CatalystInstance;

import androidx.annotation.Nullable;

public class ReactUtil {

    @Nullable
    public static String getSourceUrl(CatalystInstance instance) {
        return instance.getSourceURL();
    }

}
