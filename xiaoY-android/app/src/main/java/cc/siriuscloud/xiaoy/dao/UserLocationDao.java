package cc.siriuscloud.xiaoy.dao;

import android.util.Log;

import java.io.IOException;

import cc.siriuscloud.xiaoy.AppVessel;
import cc.siriuscloud.xiaoy.domain.UserLocation;
import cc.siriuscloud.xiaoy.utils.HttpUtil;
import cc.siriuscloud.xiaoy.domain.Message;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * 定位相关
 */
public class UserLocationDao {

    private String TAG=UserLocation.class.getName();

    private static String URL_COMMIT_LOCATION= AppVessel.BASE_URL+"/location/commitLocation.do";

    private DaoCallBack daoCallBack;

    public UserLocationDao(DaoCallBack daoCallBack) {
        this.daoCallBack = daoCallBack;
    }

    public void commitLocation(UserLocation userLocation) {


        FormBody formBody = HttpUtil.mappingFormBody(userLocation);

        HttpUtil.sendHttpRequest(URL_COMMIT_LOCATION, formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                daoCallBack.onError(Message.STATUS_ERROR, Message.MSG_ERROR, null);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //将数据转为json
                String body = response.body().string();
                Log.d(TAG,"body..................."+body);

                Message<UserLocation> msg = new Message().jsonBuildItem(body, UserLocation.class);
                Log.d(TAG,"onResponse..................."+msg);

                if (msg.getStatus() != 0) {
                    daoCallBack.onError(msg.getStatus(), Message.MSG_ERROR, null);
                } else {

                    daoCallBack.onSuccess(msg.getStatus(), msg.getMsg(), msg.getItem());
                }

            }
        });


    }
}
