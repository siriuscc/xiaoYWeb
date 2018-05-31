package cc.siriuscloud.xiaoy.view;

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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import cc.siriuscloud.xiaoy.MainActivity;
import cc.siriuscloud.xiaoy.R;
import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.viewconponent.TaskAdapter;


public class TodayFragment extends Fragment {

    private static final String TAG="TodayFragment...";
    private FloatingActionButton addTaskBtn;

    //任务列表
    private ListView tasksView;
    private List<Task> tasks;

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

        tasksView =this.getActivity().findViewById(R.id.tasks_layout);
        tasks=initTasks();

        TaskAdapter taskAdapter = new TaskAdapter(this.getActivity(), R.layout.task_item, tasks);
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



    private ArrayList<Task> initTasks() {

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task(0,"软件工程作业",new Date(2018,05,30),new Date(2018,05,30),null,1,null));
        tasks.add(new Task(1,"软件工程作业",new Date(2018,05,30),new Date(2018,05,31),null,1,null));
        tasks.add(new Task(2,"软件工程作业",new Date(2018,05,30),new Date(2018,05,31),null,1,null));
        tasks.add(new Task(3,"软件工程作业",new Date(2018,05,30),new Date(2018,05,31),null,1,null));
        return tasks;
    }

}
