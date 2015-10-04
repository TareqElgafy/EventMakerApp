package com.example.reem.eventmaker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Reem on 9/7/2015.
 */
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {       Intent service1 = new Intent(context, MyAlarmService.class);
        context.startService(service1);
    }
}
