package cc.siriuscloud.xiaoy.viewconponent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cc.siriuscloud.xiaoy.R;
import cc.siriuscloud.xiaoy.domain.Task;

public class TaskAdapter extends ArrayAdapter<Task> {

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

        //当前任务
        Task task = this.tasks.get(position);

        View taskView = LayoutInflater.from(context).inflate(resourceId,parent,false);

//                View.inflate(context, resourceId, parent);

        TextView timeTxt = taskView.findViewById(R.id.task_time_txt);
        TextView titleTxt=taskView.findViewById(R.id.task_title_txt);

        timeTxt.setText(task.getStartTime()+"/"+task.getEndTime());
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
