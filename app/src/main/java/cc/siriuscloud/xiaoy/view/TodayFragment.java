package cc.siriuscloud.xiaoy.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cc.siriuscloud.xiaoy.AppVessel;
import cc.siriuscloud.xiaoy.MainActivity;
import cc.siriuscloud.xiaoy.R;
import cc.siriuscloud.xiaoy.dao.DaoCallBack;
import cc.siriuscloud.xiaoy.dao.TaskDao;
import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.viewconponent.TaskAdapter;


public class TodayFragment extends Fragment {

    private static final String TAG=TodayFragment.class.getName();
    private FloatingActionButton addTaskBtn;

    private ProgressDialog dialog;

    //任务列表
    private ListView tasksView;
    private List<Task> tasks=new ArrayList<>();
    private TaskAdapter taskAdapter;

    //拉动刷新
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //显示遮蔽框
        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("请稍候");
        dialog.setMessage("同步数据......");
        dialog.setCancelable(false);
        dialog.show();


        tasksView =this.getActivity().findViewById(R.id.tasks_layout);
        initTasks();

        taskAdapter = new TaskAdapter(this.getActivity(), R.layout.task_item, tasks);
        tasksView.setAdapter(taskAdapter);
        tasksView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Task task = tasks.get(position);
                ((MainActivity)getActivity()).replaceFragment(TaskFragment.newInstance(task.getTaskId()));
            }
        });

        swipeRefreshLayout=this.getActivity().findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        addTaskBtn=this.getActivity().findViewById(R.id.add_task_btn);
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(new AddTaskFragment());
            }
        });
    }


    /**
     * 请求数据，初始化tasks
     */
    private void initTasks() {

        User user=AppVessel.get("user");

        TaskDao todatTaskDao = new TaskDao(new DaoCallBack<List<Task>>() {

            @Override
            public void onSuccess(int status, String msg, final List<Task> data) {

                TodayFragment.this.tasks.clear();
                TodayFragment.this.tasks.addAll(data);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        taskAdapter.notifyDataSetChanged();

                        if(dialog!=null){
                            dialog.cancel();
                        }

                    }
                });
            }

            @Override
            public void onError(int status, String msg, List<Task> data) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"加载数据失败",Toast.LENGTH_SHORT).show();

                        if(dialog!=null){
                            dialog.cancel();
                        }
                    }
                });

            }
        });

        todatTaskDao.findTodayTasks(user);
    }

}
