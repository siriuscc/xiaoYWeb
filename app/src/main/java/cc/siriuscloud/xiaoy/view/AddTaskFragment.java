package cc.siriuscloud.xiaoy.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import cc.siriuscloud.xiaoy.R;





public class AddTaskFragment extends Fragment {

//
//    private TimePicker startTimePicker;
//    private TimePicker endTimePicker;



    private TextView startTimeTxt;

    public AddTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_add_task, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        startTimePicker=getActivity().findViewById(R.id.start_time_picker);
//		startTimePicker.setIs24HourView(true);
//
//        endTimePicker=getActivity().findViewById(R.id.end_time_picker);
//        endTimePicker.setIs24HourView(true);


        startTimeTxt=getActivity().findViewById(R.id.start_time_txt);



        final StringBuffer time = new StringBuffer();
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        //实例化TimePickerDialog对象
        final TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            //选择完时间后会调用该回调函数
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time.append(" "  + hourOfDay + ":" + minute);
                //设置TextView显示最终选择的时间
                startTimeTxt.setText(time);
            }
        }, hour, minute, true);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            //选择完日期后会调用该回调函数
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //因为monthOfYear会比实际月份少一月所以这边要加1
                time.append(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
                //选择完日期后弹出选择时间对话框
                timePickerDialog.show();
            }
        }, year, month, day);
        //弹出选择日期对话框
        datePickerDialog.show();


    }


}
