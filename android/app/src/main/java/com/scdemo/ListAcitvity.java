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

import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.packagerconnection.PackagerConnectionSettings;
import com.scdemo.fenbao.demo.BussinessActivity;

import java.util.ArrayList;
import java.util.List;

public class ListAcitvity extends Activity implements DefaultHardwareBackBtnHandler {



    private ListView listView;

    private List<String> titleList = new ArrayList<>();

    private ListAdapter listAdapter;

    private EditText et;

    private EditText local;
    private Button btn_fenbao;

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
        et = findViewById(R.id.et);
        local = findViewById(R.id.localhost);
        et.setVisibility(View.GONE);
        local.setVisibility(View.GONE);
        btn_fenbao = findViewById(R.id.btn_fenbao);
        titleList.add("打开插件");
        listAdapter = new ListAdapter(titleList,this);
        listView.setAdapter(listAdapter);
        verifyStoragePermissions(ListAcitvity.this);
//        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_DEBUG_SERVER_HOST_KEY, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("debug_http_host",local.getText().toString());
//        editor.commit();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    gotoNewActivity();
                }catch (Exception e){
                    Log.d("yangjie","exception------aaa---poweronException:::"+e.getMessage());
                }
            }
        });


        btn_fenbao.setOnClickListener(v -> openFenBao());







    }

    private void openFenBao ( ) {
        startActivity(new Intent(ListAcitvity.this, BussinessActivity.class));
    }

    public void gotoNewActivity(){
        Intent in = new Intent();
        in.setClass(ListAcitvity.this, RnActivity.class);
        RnActivity.bundleName = "SCDemo";
//                RnActivity.bundleName = et.getText().toString();
//                if(et.getText().toString().length()==0||et.getText().toString().equals("")){
//                    Toast.makeText(ListAcitvity.this,"请输入插件名称",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(local.getText().toString().length()==0||local.getText().toString().equals("")){
//                    Toast.makeText(ListAcitvity.this,"请输入IP:端口调试",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                new PackagerConnectionSettings(getApplicationContext()).setDebugServerHost(local.getText().toString());
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
}
