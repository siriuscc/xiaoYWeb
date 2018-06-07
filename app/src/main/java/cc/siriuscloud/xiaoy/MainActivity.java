package cc.siriuscloud.xiaoy;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.view.PersonalFragment;
import cc.siriuscloud.xiaoy.view.PlanFragment;
import cc.siriuscloud.xiaoy.view.TodayFragment;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    //需要一个全局User
    private User user;


    private BottomNavigationBar navBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "...........MainActivity.onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //初始化全局数据
        user = AppVessel.get("user");

        if (null == user) {
            Toast.makeText(this, "用户信息错误，请重新登录", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        replaceFragment(new TodayFragment());

        navBar = findViewById(R.id.bottom_nav_bar);
//
//
        navBar.setMode(BottomNavigationBar.MODE_FIXED);       // 固定
//
//        //.设置每个Item
        navBar.addItem(new BottomNavigationItem(R.drawable.clock_tip, "今天"));
        navBar.addItem(new BottomNavigationItem(R.drawable.switch_tip, "计划"));
        navBar.addItem(new BottomNavigationItem(R.drawable.mi_tip, "我的"));

//        navBar.addItem(new BottomNavigationItem(R.drawable.zuji_tip, "足迹"));

//        //5.初始化
        navBar.initialise();

        navBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                //选择触发

                Log.d(TAG, "onTabSelected.....position：" + position);

                switch (position) {
                    case 0:
                        replaceFragment(new TodayFragment());
                        break;
                    case 1:
                        replaceFragment(new PlanFragment());
                        break;
                    case 2:
                        replaceFragment(new PersonalFragment());
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

                //当点击了其他选项，本tab就失去焦点，触发
                Log.d(TAG, "onTabUnselected.....position" + position);

            }

            @Override
            public void onTabReselected(int position) {
                //重复点击同一个，释放触发
                Log.d(TAG, "onTabReselected.....position" + position);
            }
        });


        //开启服务


        Intent intent = new Intent(this, MyLocationService.class);
        startService(intent);
    }

    public void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frameLayout, fragment);
        fragmentTransaction.commit();

    }


}
