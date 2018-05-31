package cc.siriuscloud.xiaoy.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.siriuscloud.xiaoy.R;

public class TaskFragment extends Fragment {

    // TODO: Rename and change types of parameters
    public static final String TASKID="taskId";

    private int taskId;


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



}
