package com.scdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.packagerconnection.PackagerConnectionSettings;

import java.util.ArrayList;
import java.util.List;

public class ListAcitvity extends Activity implements DefaultHardwareBackBtnHandler {



    private ListView listView;

    private List<String> titleList = new ArrayList<>();

    private ListAdapter listAdapter;

    private EditText et;

    private EditText local;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        Log.d("YJSSDF","isDebug---"+isDebug(this));
        listView = findViewById(R.id.recycleView_id);
        et = findViewById(R.id.et);
        local = findViewById(R.id.localhost);
        titleList.add("打开插件");
        listAdapter = new ListAdapter(titleList,this);
        listView.setAdapter(listAdapter);

//        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_DEBUG_SERVER_HOST_KEY, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("debug_http_host",local.getText().toString());
//        editor.commit();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent();
                in.setClass(ListAcitvity.this, RnActivity.class);
                RnActivity.bundleName = et.getText().toString();
                new PackagerConnectionSettings(getApplicationContext()).setDebugServerHost(local.getText().toString());
                startActivity(in);
            }
        });








    }

    @Override
    public void invokeDefaultOnBackPressed() {
        // super.onBackPressed();
    }


    public boolean isDebug(Context context){
        boolean isDebug = context.getApplicationInfo()!=null&&
                (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        return isDebug;
    }
}
