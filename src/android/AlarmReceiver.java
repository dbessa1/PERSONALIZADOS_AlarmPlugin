package com.uniclau.alarmplugin;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.util.Log;
import android.os.Vibrator;
import android.app.IntentService;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("AlarmPlugin", "AlarmReceived");
        
/*        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        pm.wakeUp(SystemClock.uptimeMillis());
*/
        PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        WakeLock wakeLock = pm.newWakeLock((PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
        wakeLock.acquire();
 
        KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE); 
        KeyguardLock keyguardLock =  keyguardManager.newKeyguardLock("TAG");
        keyguardLock.disableKeyguard();

        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(80);
   
        
        intent = new Intent();
        intent.setAction("com.uniclau.alarmplugin.ALARM");
        intent.setPackage(context.getPackageName());
        ////intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);//nao deu
        ////intent.addFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME | Intent.FLAG_ACTIVITY_NEW_TASK);//deu
        intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP | Intent.FLAG_ACTIVITY_TASK_ON_HOME | Intent.FLAG_ACTIVITY_NEW_TASK);//deu
        //intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        
        
            // String  packageN = "com.grantec.filhorapido";
            ////Intent i = new Intent("ACTION_MAIN");
            //i = context.getPackageManager().getLaunchIntentForPackage(packageN);
                ////i.addCategory(Intent.CATEGORY_LAUNCHER);
                ////i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ////i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
                //context.startActivity(i);


    }
}
