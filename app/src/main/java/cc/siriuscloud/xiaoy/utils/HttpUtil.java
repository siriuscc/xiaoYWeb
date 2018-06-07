package cc.siriuscloud.xiaoy.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {


    private static final String TAG = HttpUtil.class.getSimpleName();

    public static void sendHttpRequest(String url, FormBody formBody, okhttp3.Callback callback) {


        //需要一个能把Domain 映射到请求参数的工具类
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().post(formBody).url(url).build();

        client.newCall(request).enqueue(callback);

    }

    /**
     * 映射domain到FormBody
     * @param obj
     * @return
     */
    public static FormBody mappingFormBody(Object obj) {

        FormBody.Builder builder = new FormBody.Builder();

        //开了这个就可以访问
        Field[] declaredFields = obj.getClass().getDeclaredFields();

        List<Object> list = new ArrayList<>();

        //允许访问private属性
        Field.setAccessible(declaredFields, true);
        try {
            for (Field field : declaredFields) {

                String key = field.getName();
                Object value = field.get(obj);

                String format = MyDateUtil.format(value);

                if (key != null && value != null) {
                    builder.add(key, format);

                }

            }
        } catch (IllegalArgumentException e) {

            e.printStackTrace();
        } catch (IllegalAccessException e) {

            e.printStackTrace();
        }
        FormBody formBody = builder.build();

        return formBody;

    }


}
