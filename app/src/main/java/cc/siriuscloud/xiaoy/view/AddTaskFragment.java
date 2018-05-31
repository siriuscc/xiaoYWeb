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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import cc.siriuscloud.xiaoy.R;





public class AddTaskFragment extends Fragment {


    private LinearLayout startTimeLayout;

    private TextView startDateTxt;
    private TextView startTimeTxt;


    private LinearLayout endTimeLayout;

    private TextView endDateTxt;
    private TextView endTimeTxt;



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


        startTimeLayout=getActivity().findViewById(R.id.start_time_layout);

        startDateTxt=getActivity().findViewById(R.id.start_date_txt);
        startTimeTxt=getActivity().findViewById(R.id.start_time_txt);

        startTimeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //实例化对象
                DatePickerFragment datePickerFragment = new DatePickerFragment();

                datePickerFragment.setDataCallBack(new DatePickerFragment.DataCallBack() {

                    @Override
                    public void call(int year, int month, int day, int hour, int minute) {

                        Toast.makeText(getActivity(),year+":"+month+":"+day+" "+hour+":"+minute,Toast.LENGTH_SHORT).show();

                        //TODO 日期格式化

                        startDateTxt.setText(year+"年"+month+"");

                    }
                });

                //调用show方法弹出对话框
                // 第一个参数为FragmentManager对象
                // 第二个为调用该方法的fragment的标签
                datePickerFragment.show(getFragmentManager(), "date_picker");

            }
        });




        endTimeLayout=getActivity().findViewById(R.id.end_time_layout);

    }




}
