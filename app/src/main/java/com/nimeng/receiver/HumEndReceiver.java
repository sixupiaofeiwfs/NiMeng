package com.nimeng.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.nimeng.View.MainActivity;

public class HumEndReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1=new Intent();
        intent1.setClass(context, MainActivity.class);
        context.startActivity(intent1);

    }
}
