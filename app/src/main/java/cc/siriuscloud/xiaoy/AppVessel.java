package cc.siriuscloud.xiaoy;

import java.util.HashMap;


/**
 * 应用容器，用于存放全局数据
 */
public class AppVessel {

    private static HashMap<String,Object> appMap=new HashMap<>();

    private AppVessel() {
    }




    public static Object put(String key,Object value){

        return appMap.put(key,value);
    }


    public static int getInt(String key){

        return (int) appMap.get(key);
    }


    public static String getString(String key){
        return (String) appMap.get(key);
    }

    public static Object getObject(String key){
        return appMap.get(key);
    }


    public static int size(){
        return appMap.size();
    }

    public static <T> T get(String key){
        return (T) appMap.get(key);
    }


}
