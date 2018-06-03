package cc.siriuscloud.xiaoy.viewconponent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cc.siriuscloud.xiaoy.R;
import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.domain.vo.TaskDayVo;
import cc.siriuscloud.xiaoy.utils.MyStringUtils;

public class TaskExpandableListAdapter extends BaseExpandableListAdapter {


    //TODO 自己写一个vo来装数据吧


    private List<Date> groupList;
    private List<List<Task>>childList;
    private Context context;


    private int groupLayout;
    private int childLayout;

    public TaskExpandableListAdapter(List<Date> groupList, List<List<Task>> childList, Context context, int groupLayout, int childLayout) {
        this.groupList = groupList;
        this.childList = childList;
        this.context = context;
        this.groupLayout = groupLayout;
        this.childLayout = childLayout;
    }



    @Override
    public int getGroupCount() {

        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return this.groupList.get(groupPosition);

    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {


        return this.childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {


        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


        View view;


        if(convertView!=null){
            view=convertView;
        }else{
            view= LayoutInflater.from(context).inflate(groupLayout,parent,false);
        }

        Date group = (Date) getGroup(groupPosition);
        TextView titleView = view.findViewById(R.id.view_title);
        titleView.setText(MyStringUtils.DateToDay(group));

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        View taskView;

        if(convertView!=null){
            taskView=convertView;
        }else{
            taskView= LayoutInflater.from(context).inflate(childLayout,parent,false);
        }

        Task task = (Task) getChild(groupPosition,childPosition);

        // 视图数据回填
        TextView timeStartTxt = taskView.findViewById(R.id.task_time_start_txt);
        TextView timeEndTxt = taskView.findViewById(R.id.task_time_end_txt);

        TextView titleTxt=taskView.findViewById(R.id.task_title_txt);

        timeStartTxt.setText(MyStringUtils.DateToDayTime(task.getStartTime()));
        timeEndTxt.setText(MyStringUtils.DateToDayTime(task.getEndTime()));
        titleTxt.setText(task.getTitle());

        return taskView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
