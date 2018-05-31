package cc.siriuscloud.xiaoy.view;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

//实现OnTimeSetListener接口
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{


    private int year;
    private int month;
    private int day;


    //默认空回调
    private DatePickerFragment.DataCallBack dataCallBack=new DatePickerFragment.DataCallBack() {
        @Override
        public void call(int year,int month,int day,int hour, int minute) {
            //do nothing
            Log.i("TimePickerFragment","do nothing");
        }
    };

    public void setDataCallBack(DatePickerFragment.DataCallBack dataCallBack) {
        this.dataCallBack = dataCallBack;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //新建日历类用于获取当前时间
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        //返回TimePickerDialog对象
        //因为实现了OnTimeSetListener接口，所以第二个参数直接传入this
        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }

    //实现OnTimeSetListener的onTimeSet方法
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        if(dataCallBack!=null){

            //调用activity的getData方法将数据传回activity显示
            dataCallBack.call(this.year,this.month,this.day,hourOfDay,minute);
        }
    }


    public void initData(int year,int month,int day){
        this.year=year;
        this.month=month;
        this.day=day;
    }




}

