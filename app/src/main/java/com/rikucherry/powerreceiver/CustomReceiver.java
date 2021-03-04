package com.rikucherry.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {


    // 当广播接收器截获其注册的广播后，这个广播作为intent被传入此处
    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        // 通过action来判断广播事件的类型
        if (intentAction != null) {
            String toastMessage = "unknown intent action";
            switch (intentAction) {
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "Power connected!";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "Power disconnected!";
                    break;
                case Constant.ACTION_CUSTOM_BROADCAST:
                    toastMessage = "你的钮儿被按了！\n"
                    + intent.getIntExtra(Constant.RESULT_RANDOM_NUMBER_SQUARE,0);
            }

            Toast.makeText(context,toastMessage,Toast.LENGTH_SHORT).show();
        }
    }
}