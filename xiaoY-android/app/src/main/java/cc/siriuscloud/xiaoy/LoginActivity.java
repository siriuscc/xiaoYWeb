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

import java.util.ArrayList;
import java.util.List;

import cc.siriuscloud.xiaoy.dao.DaoCallBack;
import cc.siriuscloud.xiaoy.dao.UserLocationDao;
import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.domain.UserLocation;
import cc.siriuscloud.xiaoy.view.LoginFragment;


/**
 * 登录活动
 */
public class LoginActivity extends AppCompatActivity {


    //位置获得
    public LocationClient locationClient;


    private UserLocationDao userLocationDao;

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
        List<String> permissionList = getPermissionList();
        //请求权限
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);

            ActivityCompat.requestPermissions(LoginActivity.this, permissions, 1);
        } else {
//            Log.d(TAG, "权限完备");
        }



        //初始化定位回调逻辑
        userLocationDao = new UserLocationDao(new DaoCallBack<UserLocation>() {
            @Override
            public void onSuccess(int status, String msg, UserLocation data) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "提交数据成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onError(int status, String msg, UserLocation data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "提交数据失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }


    /**
     * 获取所需权限列表
     * @return
     */
    List<String> getPermissionList(){
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


        return permissionList;
    }



    /**
     * 请求定位
     */
    public void requestLocation() {

        locationClient.start();
    }

    /**
     * 替换fragment
     * @param fragment
     */
    public void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.login_main_frame_layout, fragment);
        fragmentTransaction.commit();
    }


    /**
     * 获取邮件缓存
     * @return
     */
    public String getEmailCache() {

        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);

        String email = spf.getString("email", "");

        return email;
    }

    /**
     * 设置邮件缓存
     * @param email
     */
    public void setEmailCache(String email) {
        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = spf.edit();

        edit.putString("email", email);
        edit.apply();
        edit.commit();
    }


    /**
     * 定位回调
     */
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
            //提交数据
            userLocationDao.commitLocation(userLocation);

//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//
//                    Toast.makeText(LoginActivity.this,type, Toast.LENGTH_SHORT).show();
//                }
//            });

        }
    }

}
