package com.scdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.react.packagerconnection.PackagerConnectionSettings;

public class ServerActivity extends Activity {

    private EditText et;

    private EditText local;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.server_activity_layout);

        et = findViewById(R.id.et);
        local = findViewById(R.id.localhost);

        button = findViewById(R.id.open_plugin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClass(ServerActivity.this, RnActivity.class);
                RnActivity.bundleName = et.getText().toString();
                if(et.getText().toString().length()==0||et.getText().toString().equals("")){
                    Toast.makeText(ServerActivity.this,"请输入插件名称",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(local.getText().toString().length()==0||local.getText().toString().equals("")){
                    Toast.makeText(ServerActivity.this,"请输入IP:端口调试",Toast.LENGTH_SHORT).show();
                    return;
                }
                new PackagerConnectionSettings(getApplicationContext()).setDebugServerHost(local.getText().toString());
                startActivity(in);
            }
        });
    }
}
