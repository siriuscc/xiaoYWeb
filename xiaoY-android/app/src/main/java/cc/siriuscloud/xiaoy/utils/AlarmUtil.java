package cc.siriuscloud.xiaoy.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import cc.siriuscloud.xiaoy.AlarmActivity;

/**
 * 闹钟工具类
 */
public class AlarmUtil {


    private static String TAG = "AlarmUtil";

    /**
     * 添加一个闹钟，到点触发Activity，这个方法没有用过，需要测试
     *
     * @param triggerAtTime 启动时间
     */
    @Deprecated
    public static void addAlarm(Context context, long triggerAtTime, Date date) {

        Intent intent = new Intent(context, AlarmActivity.class);
        String string = MyDateUtil.dateToString(date);
        intent.setAction(string);

        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);


        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);


//        Log.d(TAG, "............系统时间......."+System.currentTimeMillis()+"...." + new Date(System.currentTimeMillis()));
//        Log.d(TAG, "............响铃时间........"+triggerAtTime + new Date(triggerAtTime));

        //每次设置都是发送一个广播,到点发广播
        manager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pendingIntent);
    }



    /**
     *
     * 相对时间
     * 添加一个闹钟，到点自动触发定时#{AlarmActivity}
     *
     * @param triggerAtTime 启动时间
     */
    public static void addAlarmEs(Context context, Intent intent,long triggerAtTime, Date date) {

//        目标时间-当前时间 得到 距离目前的闹钟时间

//        获取当前开机时间
//        当前开始时间+ 距离目前的闹钟时间=闹钟相对时间

        // 当前时间
        long currentTime = new Date().getTime();
        // 偏置时间
        long timeOffset=triggerAtTime-currentTime;
        //当前系统启动时间
        long esTime = SystemClock.elapsedRealtime();

        Log.d(TAG, ".........triggerAtTime："+triggerAtTime);
        Log.d(TAG, ".........currentTime："+currentTime);
        Log.d(TAG, ".........timeOffset："+timeOffset);
        Log.d(TAG, ".........esTime："+esTime);


        long esClockTime=esTime+timeOffset;

        Log.d(TAG, "添加闹钟.........esClockTime："+esClockTime);



        String string = MyDateUtil.dateToString(date);
        intent.setAction(string);

        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //TODO 只能转一下时间了
        Log.d(TAG, "............系统时间......."+System.currentTimeMillis()+"...." + new Date(System.currentTimeMillis()));
        Log.d(TAG, "............响铃时间........"+triggerAtTime + new Date(triggerAtTime));

        //每次设置都是发送一个广播,到点发广播

        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, esClockTime, pendingIntent);
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


    public static void main(String[] args) {


        Calendar calendar=Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        Date time = calendar.getTime();

        System.out.println(time.getTime());


    }

}
