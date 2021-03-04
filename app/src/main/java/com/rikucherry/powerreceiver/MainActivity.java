package com.rikucherry.powerreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.rikucherry.powerreceiver.Constant.RESULT_RANDOM_NUMBER_SQUARE;

public class MainActivity extends AppCompatActivity {

    // 创建接收器的实例并初始化
    private CustomReceiver mReceiver = new CustomReceiver();
    private TextView textRandomNumber;
    private int randomNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textRandomNumber = findViewById(R.id.randomNumber);

        // 创建Intent过滤器（广播就是一种intent）
        /*
          When the system receives an Intent as a broadcast,
          it searches the broadcast receivers based on the action value
          specified in the IntentFilter object.
         */
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        // 注册系统广播接收器
        this.registerReceiver(mReceiver,filter);

        // 注册自定义本地广播接收器
        // 注意：这里只是注册接收器，并没send，send需要靠click事件触发
        // 作业： 创建随机数并显示它的平方
        randomNumber = (int) (10 + Math.random()*10);
        textRandomNumber.setText(String.valueOf(randomNumber));

        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver,
                new IntentFilter(Constant.ACTION_CUSTOM_BROADCAST));
    }


    @Override
    protected void onDestroy() {
        this.unregisterReceiver(mReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy();
    }


    /**
     * 出发自定义广播
     * @param view
     */
    public void sendCustomBroadcast(View view) {

        Intent customBroadcastIntent = new Intent(Constant.ACTION_CUSTOM_BROADCAST);
        // 作业：传递随机数平方
        customBroadcastIntent.putExtra(RESULT_RANDOM_NUMBER_SQUARE,randomNumber*randomNumber);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);

    }
}