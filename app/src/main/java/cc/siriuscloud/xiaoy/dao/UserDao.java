package cc.siriuscloud.xiaoy.dao;

import android.util.Log;

import java.io.IOException;

import cc.siriuscloud.xiaoy.domain.User;
import cc.siriuscloud.xiaoy.utils.Message;
import cc.siriuscloud.xiaoy.utils.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

import static cc.siriuscloud.xiaoy.AppVessel.BASE_URL;

public class UserDao {

    private static final String URL_LOGIN = BASE_URL+"/user/login.do";
    private static final String URL_USER_REGISTER = BASE_URL+"/user/register.do";
    private static final String TAG = UserDao.class.getName();


    private DaoCallBack daoCallBack;

    public UserDao(DaoCallBack daoCallBack) {
        this.daoCallBack = daoCallBack;

    }


    public void findUser(String email, String password) {

        User user = new User();

        user.setEmail(email);
        user.setPasswd(password);

        FormBody formBody = HttpUtil.mappingFormBody(user);

        HttpUtil.sendHttpRequest(URL_LOGIN, formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                daoCallBack.onError(Message.STATUS_ERROR, Message.MSG_ERROR, null);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //将数据转为json
                String body = response.body().string();
                Log.d(TAG,"body..................."+body);



                Message<User> msg = new Message<User>().jsonBuildItem(body, User.class);
                Log.d(TAG,"onResponse..................."+msg);

                if (msg.getStatus() != 0) {
                    daoCallBack.onError(msg.getStatus(), Message.MSG_ERROR, null);
                } else {

                    daoCallBack.onSuccess(msg.getStatus(), msg.getMsg(), msg.getItem());
                }

            }
        });
    }


    public void register(User user) {


        FormBody formBody = HttpUtil.mappingFormBody(user);

        HttpUtil.sendHttpRequest(URL_USER_REGISTER, formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                daoCallBack.onError(Message.STATUS_ERROR, Message.MSG_ERROR, null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //将数据转为json
                String body = response.body().string();
                Message<User> msg = new Message<User>().jsonBuildItem(body, User.class);

                Log.d(TAG,"..................."+msg);
                if (msg.getStatus() != 0) {
                    daoCallBack.onError(msg.getStatus(), Message.MSG_ERROR, null);
                } else {

                    daoCallBack.onSuccess(msg.getStatus(), msg.getMsg(), msg.getItem());
                }
            }
        });


    }
}
