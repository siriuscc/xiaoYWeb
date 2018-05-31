package cc.siriuscloud.xiaoy.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * @auther sirius
 * 日期选择器
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    private String date;
    private TimePickerFragment timePicker = new TimePickerFragment();



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //得到Calendar类实例，用于获取当前时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //返回日期窗口
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    //将回调逻辑注入时间选择器
    public void setDataCallBack(DatePickerFragment.DataCallBack dataCallBack) {

        this.timePicker.setDataCallBack(dataCallBack);
    }


    //返回时间窗口
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        //将日期的Picker 和时间和Picker耦合在一起
        //使用的时候只需直接调用DatePickerFragment的show()方法


        timePicker.initData(year,monthOfYear+1,dayOfMonth);

        timePicker.show(getFragmentManager(), "time_picker");

    }


    public interface DataCallBack {
        void call(int year,int month,int day,int hour, int minute);
    }
}

