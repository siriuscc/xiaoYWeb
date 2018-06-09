package cc.siriuscloud.xiaoy;


import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cc.siriuscloud.xiaoy.dao.DaoCallBack;
import cc.siriuscloud.xiaoy.dao.UserLocationDao;
import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.domain.UserLocation;
import cc.siriuscloud.xiaoy.utils.AlarmUtil;
import cc.siriuscloud.xiaoy.view.LoginFragment;

import static cc.siriuscloud.xiaoy.utils.MyDateUtil.DateToGTU0;

public class LoginActivity extends AppCompatActivity {


    public LocationClient locationClient;

    private static final String TAG = LoginActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        locationClient = new LocationClient(getApplicationContext());
        locationClient.registerLocationListener(new MyLocationListener());


        //默认登录框
        replaceFragment(new LoginFragment());


        //权限控制
        List<String> permissionList = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.SET_TIME_ZONE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.SET_TIME_ZONE);
        }

        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);

            ActivityCompat.requestPermissions(LoginActivity.this, permissions, 1);
        } else {
            Log.d(TAG, "权限完备");
        }


//        DateToGTU0(new Date());

        //系统当前时间
        long timeMillis = System.currentTimeMillis();

        Calendar instance = Calendar.getInstance();

        long timeInMillis = instance.getTimeInMillis();






        Log.d("TAG","...........timeMillis:"+timeMillis);
        Log.d("TAG","...........timeInMillis:"+timeInMillis);



//        createAlarm();
    }

    /**
     * 请求定位
     */
    public void requestLocation() {

        locationClient.start();
    }

    public void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.login_main_frame_layout, fragment);
        fragmentTransaction.commit();
    }


    public String getEmailCache() {

        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);

        String email = spf.getString("email", "");

        return email;
    }

    public void setEmailCache(String email) {
        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = spf.edit();

        edit.putString("email", email);
        edit.apply();
        edit.commit();
    }


    public void createAlarm() {


        long triggerAtTime = System.currentTimeMillis() + 1000L;

        Date time = Calendar.getInstance().getTime();

        AlarmUtil.addAlarm(this, triggerAtTime, time);

        AlarmUtil.cancelAlarm(this, time, 0);
    }


    public void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);

    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            double latitude = bdLocation.getLatitude();
            double longitude = bdLocation.getLongitude();
            int locType = bdLocation.getLocType();

            Log.d(TAG, "latitude:" + latitude + ",longitude:" + longitude);


            final String type;
            switch (locType) {
                case BDLocation.TypeGpsLocation:
                    type = "gps 定位";
                    break;
                case BDLocation.TypeNetWorkLocation:
                    type = "网络 定位";
                    break;
                case BDLocation.TypeCacheLocation:
                    type = "缓存定位";
                    break;
                case BDLocation.TypeOffLineLocation:
                    type = "离线定位";
                    break;

                default:
                    type = "未知定位" + locType;
            }


            UserLocation userLocation=new UserLocation();
            userLocation.setLongitude((float)longitude);
            userLocation.setLatitude((float)latitude);


            User user=AppVessel.get("user");

            userLocation.setUserId(user.getUserId());

            new UserLocationDao(new DaoCallBack<UserLocation>() {
                @Override
                public void onSuccess(int status, String msg, UserLocation data) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"提交数据成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onError(int status, String msg, UserLocation data) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"提交数据失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).commitLocation(userLocation);


            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Toast.makeText(LoginActivity.this,type, Toast.LENGTH_SHORT).show();

                }
            });

        }
    }


}
