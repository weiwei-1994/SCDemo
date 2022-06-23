package com.scdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class MyCustomView extends View {

    private int progress = 0;

    private int max = 100;

    private int roundWidth = 50;

    private String text = "test";
    public MyCustomView(ThemedReactContext reactContext, PipelineDraweeControllerBuilder pipelineDraweeControllerBuilder, Object o, Context context) {
        super(context);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @ReactProp(name = "titleText")
    public void setText(String jsString) {
        text = jsString;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();

        paint.setAntiAlias(true);

        paint.setColor(Color.RED);

        paint.setStrokeWidth(roundWidth);

        paint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 200, paint);

        paint.setStyle(Paint.Style.FILL);

        paint.setStrokeWidth(10);

        paint.setColor(Color.BLUE);

        TextPaint textPaint = new TextPaint();

        textPaint.setColor(Color.GREEN);

        textPaint.setTextSize(22);

        canvas.drawText(text,getWidth()/3,getWidth(),getWidth()/3,getHeight()/2,textPaint);

        int startX = 0, startY = 0, stopX = 0, stopY = 0;

        startX=0;

        stopX=500;

        for (int i = 0; i <= progress; i++) {
            startY = stopY = getHeight() / 2 + 200 - roundWidth / 2 - i * (400 - roundWidth) / max;

            double v = Math.pow((200 - roundWidth / 2), 2) - (Math.pow((startY - getHeight() / 2), 2));

            startX = (int) (getWidth() / 2 - Math.sqrt(v));

            stopX = (int) (getWidth() / 2 + Math.sqrt(v));

            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }
    }

    public void onReceiveNativeEvent() {
        WritableMap event = Arguments.createMap();
        event.putString("message", "我是js事件");
        ReactContext reactContext = (ReactContext)getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                "topChange",
                event);
    }
}
