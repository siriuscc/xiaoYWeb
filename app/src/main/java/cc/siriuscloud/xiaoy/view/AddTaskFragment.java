package cc.siriuscloud.xiaoy.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.Calendar;
import java.util.LinkedHashMap;

import cc.siriuscloud.xiaoy.R;
import cc.siriuscloud.xiaoy.broadcast.AlarmReceiver;


public class AddTaskFragment extends Fragment {


    private static final String TAG = "AddTaskFragment";


    private Calendar startCalendar;


    private final LinkedHashMap<String, Integer> remindMap = new LinkedHashMap<>();

    private LinearLayout startTimeLayout;

    private TextView startDateTxt;
    private TextView startTimeTxt;


    private LinearLayout endTimeLayout;

    private TextView endDateTxt;
    private TextView endTimeTxt;


    private MaterialSpinner remindSpinner;


    public AddTaskFragment() {
        // Required empty public constructor

        //初始化数据
        remindMap.put("不提醒", 1);
        remindMap.put("发生时", 0);
        remindMap.put("15分钟前", -15);
        remindMap.put("30分钟前", -30);
        remindMap.put("1小时前", -60 * 24);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_add_task, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //开始时间选择器 绑定
        startTimeLayout = getActivity().findViewById(R.id.start_time_layout);

        startDateTxt = getActivity().findViewById(R.id.start_date_txt);
        startTimeTxt = getActivity().findViewById(R.id.start_time_txt);

        startTimeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //实例化对象
                DatePickerFragment datePickerFragment = new DatePickerFragment();

                datePickerFragment.setDataCallBack(new DatePickerFragment.DataCallBack() {

                    @Override
                    public void call(int year, int month, int day, int hour, int minute) {

                        String dateString = String.format("%04d年%02d月%02d日", year, month, day);
                        String timeString = String.format("%02d:%02d", hour, minute);

                        startDateTxt.setText(dateString);
                        startTimeTxt.setText(timeString);

                        startCalendar = Calendar.getInstance();
                        startCalendar.set(year, month, day, hour, minute);

                        Log.d(TAG, "...............calendar........" + startCalendar.getTimeInMillis());
                    }
                });

                //调用show方法弹出对话框
                // 第一个参数为FragmentManager对象
                // 第二个为调用该方法的fragment的标签
                datePickerFragment.show(getFragmentManager(), "date_picker");

            }
        });


        //结束时间选择器绑定
        endTimeLayout = getActivity().findViewById(R.id.end_time_layout);

        endDateTxt = getActivity().findViewById(R.id.end_date_txt);
        endTimeTxt = getActivity().findViewById(R.id.end_time_txt);


        endTimeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //实例化对象
                DatePickerFragment datePickerFragment = new DatePickerFragment();

                datePickerFragment.setDataCallBack(new DatePickerFragment.DataCallBack() {

                    @Override
                    public void call(int year, int month, int day, int hour, int minute) {

                        String dateString = String.format("%04d年%02d月%02d日", year, month, day);
                        String timeString = String.format("%02d:%02d", hour, minute);

                        endDateTxt.setText(dateString);
                        endTimeTxt.setText(timeString);
                    }
                });

                //调用show方法弹出对话框
                // 第一个参数为FragmentManager对象
                // 第二个为调用该方法的fragment的标签
                datePickerFragment.show(getFragmentManager(), "date_picker");

            }
        });


        remindSpinner = getActivity().findViewById(R.id.remind_spinner);


        remindSpinner.setItems(remindMap.keySet().toArray());

        remindSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {


                Integer remindMin =
                        remindMap.values().toArray(new Integer[remindMap.size()])[position];


                //remindTime> 0 表示之后提示
                if (null == remindMin || startCalendar == null || remindMin > 0) {
                    Log.d(TAG, ".........空指针");
                    return;
                }

                addAlarm(remindMin);


            }
        });

    }

    /**
     * 添加一个闹钟，到点自动发送广播 #{AlarmReceiver.ALARM_RECEIVER_URI}
     *
     * @param remindMin 表示提前多少分钟通知，小于0
     */
    private void addAlarm(int remindMin){

        //提前通知

        Intent intent = new Intent(AlarmReceiver.ALARM_RECEIVER_URI);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),
                0, intent, 0);

        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        Log.d(TAG, "............系统时间........" + System.currentTimeMillis());

        long triggerAtTime = startCalendar.getTimeInMillis() + remindMin * 60 * 1000;

        Log.d(TAG, "............响铃时间........" + triggerAtTime);

        //每次设置都是发送一个广播,到点发广播
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);

    }




    public void showDialog(Context context) {
        final BottomSheetDialog dialog = new BottomSheetDialog(context);
        View dialogView = LayoutInflater.from(context)
                .inflate(R.layout.layout_remind, null);

        ListView listView = (ListView) dialogView.findViewById(R.id.remind_list_view);
        String[] array = new String[]{"item-1", "item-2", "item-3", "item-4", "item-5", "item-6", "item-7", "item-8", "item-9"};
        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);

        dialog.setContentView(dialogView);
        dialog.show();

    }


}
