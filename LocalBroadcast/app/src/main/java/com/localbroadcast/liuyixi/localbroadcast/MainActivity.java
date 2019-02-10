package com.localbroadcast.liuyixi.localbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    IntentFilter mIntentFilter;
    private LocalBroadcastManager broadcastManager;
    private LocalReceiver mLocalReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        broadcastManager = LocalBroadcastManager.getInstance(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.sofia.LOCAL_BROADCAST");
                //发送本地广播
                broadcastManager.sendBroadcast(intent);
            }
        });

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("com.liuyixi.LOCAL_BROADCAST");

        //注册接收本地广播
        mLocalReceiver = new LocalReceiver();
        broadcastManager.registerReceiver(mLocalReceiver,mIntentFilter);
    }

    class LocalReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"本地广播",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        broadcastManager.unregisterReceiver(mLocalReceiver);
    }
}
