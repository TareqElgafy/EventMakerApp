package com.example.reem.eventmaker;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Reem on 9/7/2015.
 */
public class MyAlarmService extends Service {



    private NotificationManager mManager;
    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }
    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);
        mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(this.getApplicationContext(),CreateEvent.class);

        Notification notification = new Notification(R.drawable.ic_launcher,CreateEvent.notificationMessage, System.currentTimeMillis());
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity( this.getApplicationContext(),0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.setLatestEventInfo(this.getApplicationContext(), "Event Maker", CreateEvent.notificationMessage, pendingNotificationIntent);
        notification.defaults|= Notification.DEFAULT_SOUND;
        notification.defaults|=Notification.DEFAULT_VIBRATE;
        mManager.notify(0, notification);    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}
