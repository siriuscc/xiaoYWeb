package cc.siriuscloud.xiaoy.viewconponent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cc.siriuscloud.xiaoy.R;
import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.utils.MyStringUtils;

public class TaskAdapter extends ArrayAdapter<Task> {

    private static final String TAG = "TaskAdapter";
    private final int resourceId;
    private Context  context;
    private List<Task>tasks;



    public TaskAdapter(@NonNull Context context, int resource, @NonNull List<Task> tasks) {
        super(context, resource, tasks);
        this.context=context;
        this.tasks=tasks;

        this.resourceId=resource;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        Log.d(TAG,".......class name.................."+this.tasks.get(position).getClass());

        //当前任务
        Task task = this.tasks.get(position);
        View taskView = LayoutInflater.from(context).inflate(resourceId,parent,false);


        // 视图数据回填
        TextView timeStartTxt = taskView.findViewById(R.id.task_time_start_txt);
        TextView timeEndTxt = taskView.findViewById(R.id.task_time_end_txt);

        TextView titleTxt=taskView.findViewById(R.id.task_title_txt);

        timeStartTxt.setText(MyStringUtils.DateToDayTime(task.getStartTime()));
        timeStartTxt.setText(MyStringUtils.DateToDayTime(task.getEndTime()));

        titleTxt.setText(task.getTitle());


        return taskView;
    }


    @Override
    public int getCount() {

        if(null==this.tasks){
            return 0;
        }

        return this.tasks.size();
    }
}
