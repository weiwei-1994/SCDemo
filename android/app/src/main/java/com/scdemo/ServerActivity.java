package com.scdemo;

import static com.scdemo.MainApplication.getContext;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.facebook.react.packagerconnection.PackagerConnectionSettings;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerActivity extends Activity {

    private EditText et;

    private EditText local;

    private Button button;

    private TextView scan_code;

    String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.VIBRATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.server_activity_layout);

        et = findViewById(R.id.et);
        local = findViewById(R.id.localhost);
        scan_code = findViewById(R.id.scan_id);
        button = findViewById(R.id.open_plugin);

        scan_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissions();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClass(ServerActivity.this, RnActivity.class);
                RnActivity.bundleName = et.getText().toString();
                if(et.getText().toString().length()==0||et.getText().toString().equals("")){
                    Toast.makeText(ServerActivity.this,"?????????????????????",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(local.getText().toString().length()==0||local.getText().toString().equals("")){
                    Toast.makeText(ServerActivity.this,"?????????IP:????????????",Toast.LENGTH_SHORT).show();
                    return;
                }
                new PackagerConnectionSettings(getApplicationContext()).setDebugServerHost(local.getText().toString());
                startActivity(in);
            }
        });
    }

    //?????????????????????????????????
    private void checkPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int i = ContextCompat.checkSelfPermission(getContext(), permissions[0]);
            int l = ContextCompat.checkSelfPermission(getContext(), permissions[1]);
            int h = ContextCompat.checkSelfPermission(getContext(), permissions[2]);
            int j = ContextCompat.checkSelfPermission(getContext(), permissions[3]);
            // ?????????????????? ?????? GRANTED---??????  DINIED---??????
            if (i != PackageManager.PERMISSION_GRANTED ||
                    l != PackageManager.PERMISSION_GRANTED ||
                    h != PackageManager.PERMISSION_GRANTED ||
                    j != PackageManager.PERMISSION_GRANTED) {
                // ??????????????????????????????????????????????????????
                startRequestPermission();
            }else{
                config();
                openScan();
            }
        }
    }
    private void startRequestPermission() {
        ActivityCompat.requestPermissions(ServerActivity.this, permissions, 321);
    }

    /**
     * ??????????????????
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
                    //??????????????????????????????????????????????????????????????????--->????????????????????????
                    Toast toast = Toast.makeText(this, "????????????????????????", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    //??????????????????,??????
                    config();
                    openScan();
                }
            }
        }
    }

    private void openScan(){
        startActivityForResult(new Intent(ServerActivity.this,
                CaptureActivity.class), 0);
    }
}
