package cc.siriuscloud.xiaoy.dao;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cc.siriuscloud.xiaoy.domain.Task;
import cc.siriuscloud.xiaoy.utils.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class TaskDao {




    public static final String URL_ADD_TASK="http://10.0.2.2:8080/task/addTask.do";

    private static final String TAG ="TaskDao" ;


    private DaoCallBack daoCallBack;


    public int addTaskDao(Task task) {



        FormBody formBody = HttpUtil.mappingFormBody(task);


        //构造请求
        HttpUtil.sendHttpRequest(URL_ADD_TASK,formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                daoCallBack.onError(0,null,null);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String jsonData = response.body().string();
                //封装一把数据，传入回调方法

                try {

                    Log.d(TAG,"..............."+jsonData);
                    JSONArray jsonArray = new JSONArray(jsonData);

                    JSONObject jsonObject = jsonArray.getJSONObject(0);
//                    int status = jsonArray.getInt("status");

                    int status = jsonObject.getInt("status");

                    //成功
                    if(status==0){

                        daoCallBack.onSuccess(0,null,null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        //发送远程请求


        //响应处理


        return 0;

    }



}
