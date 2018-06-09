package cc.siriuscloud.xiaoy.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cc.siriuscloud.xiaoy.MainActivity;
import cc.siriuscloud.xiaoy.R;
import cc.siriuscloud.xiaoy.dao.DaoCallBack;
import cc.siriuscloud.xiaoy.dao.TaskDao;
import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.utils.MyDateUtil;

/**
 * 任务详情页面
 */
public class TaskFragment extends Fragment {

    public static final String TASKID = "taskId";

    private int taskId;


    private TextView titleText;
    private TextView startDateText;
    private TextView endDateText;
    private TextView delayMinText;
    private TextView contentText;

    private Button removeTaskBtn;


    public TaskFragment() {
        // Required empty public constructor
//        throw new RuntimeException("不应该使用空参数构造器");
    }

    /**
     * 通过这个来植入参数
     * this fragment using the provided parameters.
     *
     * @param taskId .
     * @return A new instance of fragment TaskFragment.
     */
    public static TaskFragment newInstance(int taskId) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putInt(TASKID, taskId);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 在这里取初始化参数
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            taskId = getArguments().getInt(TASKID);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //绑定组件

        titleText = getActivity().findViewById(R.id.title_txt);

        startDateText = getActivity().findViewById(R.id.start_date_txt);
        endDateText = getActivity().findViewById(R.id.end_date_txt);
        delayMinText = getActivity().findViewById(R.id.delay_min_txt);
        contentText = getActivity().findViewById(R.id.content_txt);
        removeTaskBtn = getActivity().findViewById(R.id.remove_task_btn);


        removeTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Tip")
                        .setMessage("真的要删去吗？？真的要删去吗？")
                        .setCancelable(true)
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), "cancle??", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                TaskDao taskDao = new TaskDao(new DaoCallBack() {
                                    @Override
                                    public void onSuccess(int status, String msg, Object data) {

                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();

                                                //TODO 这里本来应该回退到上一窗口，回退功能还没写，算了
                                                ((MainActivity) getActivity()).replaceFragment(new TodayFragment());

                                            }
                                        });

                                    }

                                    @Override
                                    public void onError(int status, String msg, Object data) {

                                    }
                                });

                                //删除
                                taskDao.removeTask(taskId);

                            }
                        }).create();

                alertDialog.show();


            }
        });


        //获取数据
        TaskDao taskDao = new TaskDao(new DaoCallBack<Task>() {

            @Override
            public void onSuccess(int status, String msg, final Task task) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        titleText.setText(task.getTitle());
                        startDateText.setText(MyDateUtil.dateToString(task.getStartTime()));
                        endDateText.setText(MyDateUtil.dateToString(task.getEndTime()));
                        delayMinText.setText(task.getDelayMin() + "分钟前");

                        contentText.setText(task.getContent());
                    }
                });

            }

            @Override
            public void onError(int status, String msg, Task data) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "读取信息错误", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });


        taskDao.findTaskById(taskId);
    }
}
