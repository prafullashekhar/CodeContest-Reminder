package com.underdogdeveloper.codecontests.BroadCasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){

        }else{
            // calling AlarmService for notification
            Intent intentService = new Intent(context, AlarmService.class);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                context.startForegroundService(intentService);
            }else {
                context.startService(intentService);
            }
        }
    }
}
