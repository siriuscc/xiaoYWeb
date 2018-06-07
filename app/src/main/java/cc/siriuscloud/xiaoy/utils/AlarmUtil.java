package cc.siriuscloud.xiaoy.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

import cc.siriuscloud.xiaoy.AlarmActivity;

public class AlarmUtil {


    private static String TAG = "AlarmUtil";

    /**
     * 添加一个闹钟，到点自动发送广播 #{AlarmReceiver.ALARM_RECEIVER_URI}
     *
     * @param triggerAtTime 启动时间
     */
    public static void addAlarm(Context context, long triggerAtTime, Date date) {

        Intent intent = new Intent(context, AlarmActivity.class);
        String string = MyDateUtil.dateToString(date);
        intent.setAction(string);
        Log.d(TAG, "..date.getTime..........." + date.getTime());

        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);


        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Log.d(TAG, "............系统时间........" + System.currentTimeMillis());
        Log.d(TAG, "............响铃时间........" + triggerAtTime);

        //每次设置都是发送一个广播,到点发广播
        manager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pendingIntent);
    }


    public static void addAlarmService(Context context, long triggerAtTime, Intent intent) {

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                0);

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Log.d(TAG, "............系统时间........" + System.currentTimeMillis());

        Log.d(TAG, "............响铃时间........" + triggerAtTime);

        //每次设置都是发送一个广播,到点发广播
        manager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pendingIntent);


    }


    public static void cancelAlarm(Context context, Date date, int id) {


        Intent intent = new Intent(context, AlarmActivity.class);

        String action = MyDateUtil.dateToString(date);

        intent.setAction(action);

        PendingIntent pi = PendingIntent.getActivity(context, id, intent, PendingIntent
                .FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pi);
    }


}
