package cc.siriuscloud.xiaoy.utils;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class Message<T> implements Serializable {

    public static final int STATUS_ERROR = 1;
    public static final int STATUS_SUCCESS = 0;

    public static final String MSG_ERROR = "error";
    public static final String MSG_SUCCESS = "success";
    private static final String TAG = Message.class.getName();


    private int status = STATUS_SUCCESS;
    private String msg = null;
    private T item = null;
    private List<T> data = null;
    private Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();;



    public Message() {
    }


    public Message(int status) {

        this.status = status;
    }



    public Message(String msg){
        this.msg=msg;
    }



    public Message(int status, String msg) {
        this.msg=msg;
        this.status=status;

    }

    public Message(int status, String msg, T item) {
        this.status=status;
        this.msg=msg;
        this.item=item;
    }


    public Message(int status, String msg, List<T> data) {
        this.status=status;
        this.msg=msg;
        this.data=data;
    }



    public int getStatus() {
        return status;
    }

    public Message<T> setStatus(int status) {
        this.status = status;

        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Message<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public Message<T> setData(List<T> data) {
        this.data = data;
        return this;
    }


    public T getItem() {
        return item;

    }

    public Message<T> setItem(T item) {
        this.item = item;
        return this;
    }

    public Message jsonBuildItem(String jsonString, Class<T> clazz) {

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            int status = jsonObject.getInt("status");
            String msg = jsonObject.getString("msg");
            String objectString = jsonObject.getString("data");

            T obj = gson.fromJson(objectString, clazz);
            this.setItem(obj);
            return this;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Message<T> jsonBuildData(String jsonString, Class<T> clazz) {

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            int status = jsonObject.getInt("status");
            String message = jsonObject.getString("msg");
            String objectString = jsonObject.getString("data");

            JSONArray dataArr = jsonObject.getJSONArray("data");

            ArrayList<T> dataList = new ArrayList<T>();

            for (int i = 0; i < dataArr.length(); ++i) {
                String string = dataArr.getString(i);

                T item = gson.fromJson(string, clazz);
                dataList.add(item);
            }

            this.setData(dataList);
            return this;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String toString() {
        return "Message{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
