package cc.siriuscloud.xiaoy.dao;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import cc.siriuscloud.xiaoy.AppVessel;
import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.utils.HttpUtil;
import cc.siriuscloud.xiaoy.domain.Message;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

import static cc.siriuscloud.xiaoy.AppVessel.BASE_URL;

/**
 * 任务的Dao层，负责完成远程数据的发送和接受，视图的修改由#{DaoCallBack.class} 注入
 * 注意线程的问题，回调方法在子线程中执行，所以不能直接修改视图
 */
public class TaskDao {

    public TaskDao(DaoCallBack daoCallBack) {
        this.daoCallBack = daoCallBack;
    }


    public static final String URL_ADD_TASK = BASE_URL+"/task/addTask.do";
    public static final String URL_FIND_TODAY_TASK = BASE_URL+"/task/findTodayTasks.do";
    public static final String URL_FIND_TASK_BY_USERID = BASE_URL+"/task/findTask.do";
    public static final String URL_REMOVE_TASK = BASE_URL+"/task/removeTask.do";
    public static final String URL_FIND_ALL_TASK=BASE_URL+"/task/findAllTasks.do";



    private static final String TAG = "TaskDao";


    private DaoCallBack daoCallBack;


    public void addTaskDao(Task task) {

        FormBody formBody = HttpUtil.mappingFormBody(task);

        //构造请求
        HttpUtil.sendHttpRequest(URL_ADD_TASK, formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                daoCallBack.onError(0, null, null);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String jsonData = response.body().string();
                //封装一把数据，传入回调方法

                try {

                    Log.d(TAG, "..............." + jsonData);

                    JSONObject jsonObject = new JSONObject(jsonData);
//                    int status = jsonArray.getInt("status");

                    int status = jsonObject.getInt("status");



                    //成功
                    if (status == 0) {

                        daoCallBack.onSuccess(0, null, null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


    public void findTodayTasks(User user) {


        FormBody formBody = HttpUtil.mappingFormBody(user);

        HttpUtil.sendHttpRequest(URL_FIND_TODAY_TASK, formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                daoCallBack.onError(0, null, null);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String jsonData = response.body().string();
                //封装一把数据，传入回调方法

                Message<Task> message = new Message<Task>().jsonBuildData(jsonData,Task.class);

                Log.d(TAG,"............."+message);


                List<Task> data = message.getData();

                if (message.getData() != null) {

                    daoCallBack.onSuccess(message.getStatus(), message.getMsg(), message.getData());
                }else{
                    daoCallBack.onError(message.getStatus(),message.getMsg(),null);
                }

            }
        });
    }

    public void findTaskById(int taskId) {

        User user = AppVessel.get("user");

        Task task = new Task();

        task.setUserId(user.getUserId());
        task.setTaskId(taskId);

        FormBody formBody = HttpUtil.mappingFormBody(task);


        HttpUtil.sendHttpRequest(URL_FIND_TASK_BY_USERID, formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                daoCallBack.onError(0, null, null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String jsonData = response.body().string();
                //封装一把数据，传入回调方法
                Log.d(TAG,"jsonData.............."+jsonData);

                Message<Task> message = new Message<Task>().jsonBuildItem(jsonData,Task.class);

                Log.d(TAG,"message.........."+message);


                if (message.getStatus()!=0) {
                    daoCallBack.onError(message.getStatus(),message.getMsg(),null);

                }else{
                    daoCallBack.onSuccess(message.getStatus(), message.getMsg(), message.getItem());

                }

            }
        });

    }

    /**
     * 删除任务
     * @param taskId
     */
    public void removeTask(int taskId) {

        User user = AppVessel.get("user");

        Task task = new Task();

        task.setUserId(user.getUserId());
        task.setTaskId(taskId);

        FormBody formBody = HttpUtil.mappingFormBody(task);


        HttpUtil.sendHttpRequest(URL_REMOVE_TASK, formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                daoCallBack.onError(0, null, null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String jsonData = response.body().string();
                //封装一把数据，传入回调方法
                Log.d(TAG,"jsonData.............."+jsonData);

                Message<Task> message = new Message<Task>().jsonBuildItem(jsonData,Task.class);

                Log.d(TAG,"message.........."+message);

                if (message.getStatus()!=0) {
                    daoCallBack.onError(message.getStatus(),message.getMsg(),null);

                }else{
                    daoCallBack.onSuccess(message.getStatus(), message.getMsg(), message.getItem());

                }
            }
        });


    }


    /**
     * 查找所有任务
     */
    public void findAllTasks(){

        User user = AppVessel.get("user");

        FormBody formBody = HttpUtil.mappingFormBody(user);

        HttpUtil.sendHttpRequest(URL_FIND_ALL_TASK, formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                daoCallBack.onError(0, null, null);

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String jsonData = response.body().string();
                //封装一把数据，传入回调方法
                Message<Task> message = new Message<Task>().jsonBuildData(jsonData,Task.class);

                List<Task> data = message.getData();

                if (message.getData() != null) {

                    daoCallBack.onSuccess(message.getStatus(), message.getMsg(), message.getData());
                }else{
                    daoCallBack.onError(message.getStatus(),message.getMsg(),null);
                }
            }
        });


    }
}
