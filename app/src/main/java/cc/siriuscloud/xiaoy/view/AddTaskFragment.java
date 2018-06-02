package cc.siriuscloud.xiaoy.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.Calendar;
import java.util.LinkedHashMap;

import cc.siriuscloud.xiaoy.AppVessel;
import cc.siriuscloud.xiaoy.MainActivity;
import cc.siriuscloud.xiaoy.R;
import cc.siriuscloud.xiaoy.broadcast.AlarmReceiver;
import cc.siriuscloud.xiaoy.dao.DaoCallBack;
import cc.siriuscloud.xiaoy.dao.TaskDao;
import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.domain.User;


public class AddTaskFragment extends Fragment {


    private static final String TAG = "AddTaskFragment";
    private final LinkedHashMap<String, Integer> remindMap = new LinkedHashMap<>();


    private ProgressDialog dialog;


    private Calendar startCalendar=Calendar.getInstance();         // 开始时间
    private Calendar endCalendar=Calendar.getInstance();           //结束时间


    private LinearLayout startTimeLayout;       // 开始时间点的布局
    private TextView startDateTxt;              //开始日期
    private TextView startTimeTxt;              //开始时间


    private LinearLayout endTimeLayout;         //结束时间点布局
    private TextView endDateTxt;                //结束日期
    private TextView endTimeTxt;                //结束时间


    private EditText contentEdit;                   //内容
    private EditText titleEdit;                     //标题


    private MaterialSpinner remindSpinner;      //下拉刷新组件


    private Button submitTaskBtn;                   //提交按钮


    private Integer delayMin;

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

        initPicker();

        contentEdit=getActivity().findViewById(R.id.content_edit);
        titleEdit=getActivity().findViewById(R.id.title_edit);

        //提醒时间 绑定逻辑
        remindSpinner = getActivity().findViewById(R.id.remind_spinner);
        remindSpinner.setItems(remindMap.keySet().toArray());
        remindSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                //获取延时时间
                AddTaskFragment.this.delayMin = remindMap.values().
                        toArray(new Integer[remindMap.size()])[position];

                //设置到全局
                //remindTime> 0 表示之后提示
                if (null == delayMin || startCalendar == null || delayMin > 0) {
                    Log.d(TAG, ".........空指针");
                    delayMin =null;
                    return;
                }
            }
        });


        submitTaskBtn=getActivity().findViewById(R.id.submit_task_btn);

        //提交按钮
        submitTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new ProgressDialog(getActivity());

                dialog.setTitle("添加任务");
                dialog.setMessage("提交中......");

                Task task = extractTask();

                Log.d(TAG,"..............task is ......"+task);


                TaskDao taskDao=new TaskDao(new DaoCallBack() {
                    @Override
                    public void onSuccess(int status, String msg, Object data) {

                        if(null != AddTaskFragment.this.dialog){
                            dialog.cancel();
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(),"添加成功",Toast.LENGTH_SHORT).show();

                            }
                        });

                        ((MainActivity)(getActivity())).replaceFragment(new TodayFragment());
                    }

                    @Override
                    public void onError(int status, String msg, Object data) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(),"添加失败",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
                taskDao.addTaskDao(task);
            }
        });

    }

    private Task extractTask() {

        Task task=new Task();

        User user=AppVessel.get("user");

        task.setUserId(user.getUserId());
        task.setStartTime(this.startCalendar.getTime());
        task.setEndTime(this.endCalendar.getTime());
        task.setContent(this.contentEdit.getText().toString());
        task.setTitle(this.titleEdit.getText().toString());

        if(null == this.delayMin){
            this.delayMin=0;
        }
        task.setDelayMin(this.delayMin);
        Log.d(TAG,task+"");

        return task;
    }


    /**
     * 初始化时间选择器组件绑定
     * 初始化组件回调方法
     */
    private void initPicker(){

        //开始时间选择器 绑定 逻辑
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
                    public void call(Calendar calendar, int year, int month, int day, int hour, int minute) {

                        String dateString = String.format("%04d年%02d月%02d日", year, month+1, day);
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


        //结束时间选择器绑定 逻辑
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
                    public void call(Calendar calendar, int year, int month, int day, int hour, int minute) {

                        String dateString = String.format("%04d年%02d月%02d日", year, month+1, day);
                        String timeString = String.format("%02d:%02d", hour, minute);

                        endCalendar = Calendar.getInstance();
                        endCalendar.set(year, month, day, hour, minute);

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

    }


    /**
     * 添加一个闹钟，到点自动发送广播 #{AlarmReceiver.ALARM_RECEIVER_URI}
     *
     * @param remindMin 表示提前多少分钟通知，小于0
     */
    private void addAlarm(int remindMin) {

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




}
