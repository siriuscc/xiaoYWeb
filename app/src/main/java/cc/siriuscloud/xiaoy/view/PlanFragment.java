package cc.siriuscloud.xiaoy.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cc.siriuscloud.xiaoy.MainActivity;
import cc.siriuscloud.xiaoy.R;
import cc.siriuscloud.xiaoy.dao.DaoCallBack;
import cc.siriuscloud.xiaoy.dao.TaskDao;
import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.utils.MyDateUtil;
import cc.siriuscloud.xiaoy.viewconponent.TaskExpandableListAdapter;


/**
 * 任务窗口，也就是所有任务
 */
public class PlanFragment extends Fragment {


    private static final String TAG = PlanFragment.class.getName();

    private ExpandableListView expandList;

    private List<Date> groupList = new ArrayList<>();
    private List<List<Task>> childList = new ArrayList<>();

    private ProgressDialog dialog;      //遮蔽框



    private TaskExpandableListAdapter taskExpandableListAdapter;


    public PlanFragment() {


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //显示遮蔽框
        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("请稍候");
        dialog.setMessage("提交中......");
        dialog.setCancelable(false);

        dialog.show();



        expandList = getActivity().findViewById(R.id.expand_list);


        taskExpandableListAdapter = new TaskExpandableListAdapter(groupList, childList,
                getActivity(),
                R.layout.item_group_exlist,
                R.layout.task_item
        );


        expandList.setAdapter(taskExpandableListAdapter);

        expandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                groupList.get(groupPosition);


                Task task = childList.get(groupPosition).get(childPosition);

                ((MainActivity)getActivity()).replaceFragment(TaskFragment.newInstance(task.getTaskId()));
                return true;
            }
        });



        TaskDao taskDao = new TaskDao(new DaoCallBack<List<Task>>() {
            @Override
            public void onSuccess(int status, String msg, List<Task> data) {

                initData(data);
//                printData();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        taskExpandableListAdapter.notifyDataSetChanged();

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

                        Toast.makeText(getActivity(), "获取信息失败", Toast.LENGTH_SHORT).show();
                        if(dialog!=null){
                            dialog.cancel();
                        }
                    }
                });

            }
        });

        taskDao.findAllTasks();
    }




    private void initData(List<Task> tasks) {

        if (tasks.size() < 1) {
            return;
        }

        Task preTask = tasks.get(0);
        Date currentGroup = preTask.getStartTime();
        groupList.add(currentGroup);

        List<Task> currentTaskList = new ArrayList<>();
        currentTaskList.add(preTask);

        for (int i = 1; i < tasks.size(); ++i) {

            Task task = tasks.get(i);

            if (MyDateUtil.sameDate(task.getStartTime(), preTask.getStartTime())) {

                currentTaskList.add(task);
            } else {
                //开始一个新的归类
                currentGroup = task.getStartTime();

                groupList.add(currentGroup);
                //把字列表添加到list
                childList.add(currentTaskList);
                currentTaskList = new ArrayList<>();
                currentTaskList.add(task);
            }
        }

        //还有最后一个
        childList.add(currentTaskList);

    }

    private void printData(){


        Log.d(TAG," groupList size:..........."+groupList.size());

        Log.d(TAG," childList size:............"+childList.size());

        for(int i=0;i<groupList.size();++i){

            Log.d(TAG,"group:......"+ MyDateUtil.dateToDay(groupList.get(i)));

            for(int j=0;j<childList.get(j).size();++j){

                Log.d(TAG,"................."+childList.get(j));

            }
        }

    }
}
