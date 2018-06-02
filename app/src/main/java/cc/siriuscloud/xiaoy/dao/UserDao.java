package cc.siriuscloud.xiaoy.dao;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.utils.Msg;
import cc.siriuscloud.xiaoy.utils.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class UserDao {


    private DaoCallBack daoCallBack;

    public UserDao(DaoCallBack daoCallBack) {
        this.daoCallBack=daoCallBack;
        
    }

    private static final String URL_LOGIN="http://10.0.2.2:8080/user/login.do";


    public void findUser(String email, String password) {

        User user=new User();

        user.setEmail(email);
        user.setPasswd(password);

        FormBody formBody = HttpUtil.mappingFormBody(user);

        HttpUtil.sendHttpRequest(URL_LOGIN, formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                daoCallBack.onError(Msg.STATUS_ERROR,Msg.MSG_ERROR,null);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //将数据转为json

                JSONArray jsonArray = null;
                try {

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    int status = jsonObject.getInt("status");

                    if(status!=0){
                        daoCallBack.onError(status,Msg.MSG_ERROR,null);
                    }else{

                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        User returnUser = gson.fromJson(data, User.class);

                        daoCallBack.onSuccess(status, Msg.MSG_SUCCESS,returnUser);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
