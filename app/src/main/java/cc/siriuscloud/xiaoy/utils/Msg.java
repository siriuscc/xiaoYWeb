package cc.siriuscloud.xiaoy.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Msg implements Serializable{

    public static final int STATUS_ERROR=1;
    public static final int STATUS_SUCCESS=0;

    public static final String MSG_ERROR="error";
    public static final String  MSG_SUCCESS="success";



    private int status=STATUS_SUCCESS;
    private String msg=null;
    private Object data=null;

    public Msg() {
    }

    public Msg(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    public Msg(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }



    public Msg(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Msg(int status) {
        this.status=status;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }





    public static <T> T getDataObj(String jsonString,Class <T>clazz){


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
            String objectString = jsonObject.getString("data");

            Gson gson = new Gson();

            T obj = gson.fromJson(objectString, clazz);

            return obj;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static <T> List<T> getList(String jsonString){

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
            String objectString = jsonObject.getString("data");


            Type listType = new TypeToken<ArrayList<T>>(){}.getType();
            List<T> datalist = new Gson().fromJson(objectString, listType);

            return datalist;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 转换为List
     * @param jsonString
     * @param <T>
     * @return
     */
    public static <T> Msg getInstanceWithData(String jsonString,Class <T>clazz){

        JSONObject jsonObject = null;
        Msg msg = new Msg();

        try {
            jsonObject = new JSONObject(jsonString);

            int status = jsonObject.getInt("status");
            String message=jsonObject.getString("msg");
            String objectString = jsonObject.getString("data");

            Gson gson = new Gson();

            T obj = gson.fromJson(objectString, clazz);
            msg.setData(obj);

            return msg;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 转换为List
     * @param jsonString
     * @param <T>
     * @return
     */
    public static <T> Msg getInstanceWithListData(String jsonString){

        JSONObject jsonObject = null;
        Msg msg = new Msg();

        try {
            jsonObject = new JSONObject(jsonString);

            int status = jsonObject.getInt("status");
            String message=jsonObject.getString("msg");
            String objectString = jsonObject.getString("data");

            Type listType = new TypeToken<ArrayList<T>>(){}.getType();
            List<T> datalist = new Gson().fromJson(objectString, listType);

            msg.setData(datalist);
            return msg;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
