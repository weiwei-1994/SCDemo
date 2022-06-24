package com.scdemo.rectUI;

import android.view.View;
import android.view.View.OnClickListener;

import androidx.annotation.NonNull;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.scdemo.MyCustomView;

import java.util.Map;

public class ReactViewManager extends SimpleViewManager<MyCustomView> {

    public static final String REACT_CLASS = "RCTCustomView";
    ReactApplicationContext reactApplicationContext;
    public String text = "test";
    public MyCustomView myCustomView;

    public ReactViewManager(ReactApplicationContext reactContext){
        this.reactApplicationContext = reactContext;
    }

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "titleText")
    public void setText(MyCustomView view,String jsString) {
        this.myCustomView = view;
         view.setText(jsString);
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public void setRefresh(){
        myCustomView.invalidate();
    }

    @NonNull
    @Override
    protected MyCustomView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new MyCustomView(reactContext, Fresco.newDraweeControllerBuilder(),null,reactApplicationContext);
    }

    @Override
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put(
                        "topChange",
                        MapBuilder.of(
                                "phasedRegistrationNames",
                                MapBuilder.of("bubbled", "onChange")))
                .build();
    }

}
