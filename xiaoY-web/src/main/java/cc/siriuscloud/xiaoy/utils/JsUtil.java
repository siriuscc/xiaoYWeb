package cc.siriuscloud.xiaoy.utils;

public class JsUtil {


    /**
     * js 重定向
     * @param msg
     * @param path
     * @return
     */
    public static String redirectJs(String msg,String path){

        String url="<script>alert('"+msg+"');window.location.href='"+path+"';</script>";


        return url;

    }
}
