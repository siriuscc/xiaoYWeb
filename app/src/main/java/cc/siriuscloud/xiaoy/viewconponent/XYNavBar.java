package cc.siriuscloud.xiaoy.viewconponent;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;



public class XYNavBar extends BottomNavigationBar {

    private static final String TAG="XYNavBar";


    public XYNavBar(Context context) {
        super(context);

//        this.setMode(BottomNavigationBar.MODE_FIXED);       // 固定

//        //.设置每个Item
//        this.addItem(new BottomNavigationItem(R.drawable.clock_tip, "今天"));
//        this.addItem(new BottomNavigationItem(R.drawable.switch_tip, "计划"));
//        this.addItem(new BottomNavigationItem(R.drawable.zuji_tip, "足迹"));
//        this.addItem(new BottomNavigationItem(R.drawable.mi_tip, "我的"));
//
//
//        //5.初始化
//        this.initialise();
//
//        this.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(int position) {
//                //选择触发
//
//                Log.d(TAG, "onTabSelected.....position：" + position);
//            }
//
//            @Override
//            public void onTabUnselected(int position) {
//
//                //当点击了其他选项，本tab就失去焦点，触发
//
//                Log.d(TAG, "onTabUnselected.....position" + position);
//
//            }
//
//            @Override
//            public void onTabReselected(int position) {
//
//                //重复点击同一个，释放触发
//                Log.d(TAG, "onTabReselected.....position" + position);
//
//            }
//        });

    }





    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);



    }
}
