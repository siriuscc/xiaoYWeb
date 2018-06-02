package cc.siriuscloud.xiaoy.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Message<T> implements Serializable {

    public static final int STATUS_ERROR = 1;
    public static final int STATUS_SUCCESS = 0;

    public static final String MSG_ERROR = "error";
    public static final String MSG_SUCCESS = "success";


    private int status = STATUS_SUCCESS;
    private String msg = null;
    private T data = null;


    private Message() {
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private Message jsonBuildInstance(String jsonString, Class<T> clazz) {

        Message message = new Message();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            int status = jsonObject.getInt("status");
            String msg = jsonObject.getString("msg");
            String objectString = jsonObject.getString("data");

            Gson gson = new Gson();
            T obj = gson.fromJson(objectString, clazz);
            message.setData(obj);
            return message;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Message jsonBuildWithList(String jsonString) {

        Message msg = new Message();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            int status = jsonObject.getInt("status");
            String message = jsonObject.getString("msg");
            String objectString = jsonObject.getString("data");

            Type listType = new TypeToken<ArrayList<T>>() {
            }.getType();
            List<T> datalist = new Gson().fromJson(objectString, listType);

            msg.setData(datalist);
            return msg;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


}
