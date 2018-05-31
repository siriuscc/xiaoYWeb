package cc.siriuscloud.xiaoy.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;



public class AlarmReceiver extends BroadcastReceiver {



    public static String ALARM_RECEIVER_URI="cc.siriuscloud.broadcast.ALARM_BROADCAST";



    @Override
    public void onReceive(Context context, Intent intent) {

        //接受到信息
        Toast.makeText(context,"接收者收到信息",Toast.LENGTH_SHORT).show();
    }
}
