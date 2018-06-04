package cc.siriuscloud.xiaoy.utils;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Msg implements Serializable{

//    public static final int STATUS_ERROR=1;
//    public static final int STATUS_SUCCESS=0;
//
//    public static final String MSG_ERROR="error";
//    public static final String  MSG_SUCCESS="success";
//
//
//
//    private int status=STATUS_SUCCESS;
//    private String msg=null;
//    private Object data=null;
//
//    public Msg() {
//    }
//
//    public Msg(String msg, Object data) {
//        this.msg = msg;
//        this.data = data;
//    }
//
//    public Msg(int status, String msg) {
//        this.status = status;
//        this.msg = msg;
//    }
//
//
//
//    public Msg(int status, String msg, Object data) {
//        this.status = status;
//        this.msg = msg;
//        this.data = data;
//    }
//
//    public Msg(int status) {
//        this.status=status;
//    }
//
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public Object getData() {
//        return data;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
//
//
//
//    public static <T> T getDataObj(String jsonString,Class <T>clazz){
//
//
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = new JSONObject(jsonString);
//            String objectString = jsonObject.getString("data");
//
//
//            Gson gson = new Gson();
//
//            T obj = gson.fromJson(objectString, clazz);
//
//
//            return obj;
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
}
