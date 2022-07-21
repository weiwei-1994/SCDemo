package com.scdemo;

import static com.scdemo.MainApplication.getContext;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.packagerconnection.PackagerConnectionSettings;
import com.scdemo.fenbao.demo.BussinessActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListAcitvity extends Activity implements DefaultHardwareBackBtnHandler {



    private ListView listView;

    private List<String> titleList = new ArrayList<>();

    private ListAdapter listAdapter;

    private EditText et;

    private EditText local;
    private Button btn_fenbao;

    private TextView scan_code;

    String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.VIBRATE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        Log.d("YJSSDF","isDebug---"+isDebug(this));
        listView = findViewById(R.id.recycleView_id);
        et = findViewById(R.id.et);
        local = findViewById(R.id.localhost);
        btn_fenbao = findViewById(R.id.btn_fenbao);
        titleList.add("打开插件");
        scan_code = findViewById(R.id.scan_code);
        listAdapter = new ListAdapter(titleList,this);
        listView.setAdapter(listAdapter);

//        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_DEBUG_SERVER_HOST_KEY, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("debug_http_host",local.getText().toString());
//        editor.commit();

        scan_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissions();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent();
                in.setClass(ListAcitvity.this, RnActivity.class);
                RnActivity.bundleName = et.getText().toString();
                if(et.getText().toString().length()==0||et.getText().toString().equals("")){
                    Toast.makeText(ListAcitvity.this,"请输入插件名称",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(local.getText().toString().length()==0||local.getText().toString().equals("")){
                    Toast.makeText(ListAcitvity.this,"请输入IP:端口调试",Toast.LENGTH_SHORT).show();
                    return;
                }
                new PackagerConnectionSettings(getApplicationContext()).setDebugServerHost(local.getText().toString());
                startActivity(in);
            }
        });


        btn_fenbao.setOnClickListener(v -> openFenBao());







    }

    //点击按钮，访问如下方法
    private void checkPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int i = ContextCompat.checkSelfPermission(getContext(), permissions[0]);
            int l = ContextCompat.checkSelfPermission(getContext(), permissions[1]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED ||
                    l != PackageManager.PERMISSION_GRANTED ) {
                // 如果没有授予该权限，就去提示用户请求
                startRequestPermission();
            }else{
                config();
                openScan();
            }
        }
    }
    private void startRequestPermission() {
        ActivityCompat.requestPermissions(ListAcitvity.this, permissions, 321);
    }

    /**
     * 提高屏幕亮度
     */
    private void config() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = 1.0f;
        getWindow().setAttributes(lp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            Log.d("yj","result-----result::"+result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                String pluginName = jsonObject.getString("name");
                String ip = jsonObject.getString("ip");
                Log.d("yj","result-----plugin::"+pluginName);
                Log.d("yj","result-----ip::"+ip);
                if(!TextUtils.isEmpty(pluginName)){
                    et.setText(pluginName);
                }
                if(!TextUtils.isEmpty(ip)){
                    local.setText(ip+":8081");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 321) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    //如果没有获取权限，那么可以提示用户去设置界面--->应用权限开启权限
                    Toast toast = Toast.makeText(this, "设置界面获取权限", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    //获取权限成功,跳转
                    config();
                    openScan();
                }
            }
        }
    }

    private void openScan(){
        startActivityForResult(new Intent(ListAcitvity.this,
                CaptureActivity.class), 0);
    }

    private void openFenBao ( ) {
        startActivity(new Intent(ListAcitvity.this, BussinessActivity.class));
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
