package cc.siriuscloud.xiaoy.view;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

//实现OnTimeSetListener接口

/**
 * 时间选择窗口
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{



    private int year;
    private int month;
    private int day;


    private TimeCallBack timeCallBack;

    public void setTimeCallBack(TimeCallBack timeCallBack) {

        this.timeCallBack = timeCallBack;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //新建日历类用于获取当前时间
        Calendar calendar=Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        //返回TimePickerDialog对象
        //因为实现了OnTimeSetListener接口，所以第二个参数直接传入this
        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }

    //实现OnTimeSetListener的onTimeSet方法
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


        //回调方法返回数据到日期选择器
        timeCallBack.call(hourOfDay,minute);

    }


    public void initData(int year,int month,int day){

        this.year=year;
        this.month=month;
        this.day=day;

    }



    public interface TimeCallBack{

        public void call(int hour,int min);
    }


}

