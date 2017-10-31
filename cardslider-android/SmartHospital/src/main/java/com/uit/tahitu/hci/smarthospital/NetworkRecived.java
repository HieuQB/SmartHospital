package com.uit.tahitu.hci.smarthospital;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by HieuMinh on 10/31/2017.
 */

public class NetworkRecived extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Bạn vừa bật hoặc tắt mạng nhaa !", Toast.LENGTH_SHORT).show();
    }
}
