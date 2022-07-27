package com.scdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceEventListener;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.packagerconnection.PackagerConnectionSettings;
import com.scdemo.fenbao.demo.BussinessActivity;
import com.scdemo.viewutils.BottomDialog;

import java.util.ArrayList;
import java.util.List;

public class ListAcitvity extends Activity implements DefaultHardwareBackBtnHandler, ReactInstanceEventListener {



    private ListView listView;

    private List<String> titleList = new ArrayList<>();

    private ListAdapter listAdapter;
    private BottomDialog bottomDialog;
    private boolean isBundle;
    private boolean isLoaded;

    private static String[] PERMISSIONS_STORAGE = {
        "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE" };
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        Log.d("YJSSDF","isDebug---"+isDebug(this));
        listView = findViewById(R.id.recycleView_id);
        titleList.add("调试app");
        listAdapter = new ListAdapter(titleList,this);
        listView.setAdapter(listAdapter);
        verifyStoragePermissions(ListAcitvity.this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bottomDialog = new BottomDialog(ListAcitvity.this);
                bottomDialog.setOnListener(new BottomDialog.OnHintDialogListener() {
                    @Override
                    public void onClickServer() {
                        gotoServerActivity();
                    }

                    @Override
                    public void onClickBundle() {
                        isBundle = true;
                        if(isLoaded){
                            openFenBao("asset");
                        }else {
                            loadCommonAssets();
                        }
                    }

                    @Override
                    public void onClickFile() {
                        isBundle = false;
                        if(isLoaded){
                            openFenBao("file");
                        }else {
                            loadCommonAssets();
                        }
                    }

                    @Override
                    public void onClickCancel() {
                        bottomDialog.dismiss();
                    }
                });
                bottomDialog.show();
            }
        });

    }

    private void loadCommonAssets(){
        ReactInstanceManager reactInstanceManager = ((ReactApplication)getApplication()).getReactNativeHost().getReactInstanceManager();

        //监听加载结束的回调
        reactInstanceManager.addReactInstanceEventListener(ListAcitvity.this);

        if (!reactInstanceManager.hasStartedCreatingInitialContext()){
            reactInstanceManager.createReactContextInBackground();
        }
        isLoaded = true;
    }


    private void openFenBao (String scriptType) {
        Intent in = new Intent();
        in.setClass(ListAcitvity.this, BussinessActivity.class);
        Constance.SCRIPTTYPE = scriptType;
        startActivity(new Intent(ListAcitvity.this, BussinessActivity.class));
    }

    public void gotoServerActivity(){
        Intent in = new Intent();
        in.setClass(ListAcitvity.this, ServerActivity.class);
        startActivity(in);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        // super.onBackPressed();
    }

    //然后通过一个函数来申请

    public  void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.READ_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }else {
                //gotoNewActivity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onRequestPermissionsResult (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_EXTERNAL_STORAGE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
               // gotoNewActivity();
            }
        }
    }


    public boolean isDebug(Context context){
        boolean isDebug = context.getApplicationInfo()!=null&&
                (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        return isDebug;
    }

    @Override
    public void onReactContextInitialized(ReactContext reactContext) {
        if(isBundle)
            openFenBao("asset");
        else
            openFenBao("file");
    }
}
