package cc.siriuscloud.xiaoy;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class MyLocationService extends Service {

    private static final String TAG=MyLocationService.class.getName();

    public MyLocationService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG,".................创建服务");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //做一些事情
//
//        LocationClientOption lco = new LocationClientOption();
//        //定位时间间隔，就是我们上面说的f服务要实现的功能
//        //f服务的运行才能保证一直定位
//        // span参数不能小于1000ms
//        lco.setScanSpan(4000);
//        //初始化定位客户端
//        LocationClient mlc = new LocationClient(getApplicationContext());
//        //给定位客户端注册自定义监听器
//
//        mlc.registerLocationListener(new LocationListener());
//
//
//        //设置一些配置选项
//        //当然这个lco配置还有很多，具体在下面我会列举，
//        mlc.setLocOption(lco);
//        //启动定位客户端
//        mlc.start();


        return super.onStartCommand(intent, flags, startId);


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
