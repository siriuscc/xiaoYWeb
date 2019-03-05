package cc.siriuscloud.xiaoy.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * 日期工具类
 */
public class MyDateUtil {

    private static final String TAG = "MyDateUtil";

    private static final String DATA_PATTERN = "YYYY-MM-dd HH:mm:ss";

    private static final String TIME_PATTERN = "HH:mm";

    private static final String DATA_DAY_PATTERN = "YYYY-MM-dd";


    /**
     * 日期的转换默认GMT8
     * @param obj
     * @return
     */
    public static String format(Object obj) {

        if (obj == null) {
            return null;
        }

        if (obj instanceof Date) {
            return dateGMT8ToString((Date) obj);
        }

        if (obj instanceof Integer) {

            return obj + "";
        }

        return obj + "";
    }


    /**
     * 时区为GMT8
     * 转换为 "YYYY-MM-dd HH:mm:ss"
     * @param date
     * @return
     */
    public static String dateGMT8ToString(Date date) {

        DateFormat fmt = new SimpleDateFormat(DATA_PATTERN);
        fmt.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        return fmt.format(date);
    }

    /**
     * 转换为"YYYY-MM-dd HH:mm:ss";
     * @param date
     * @return
     */
    public static String dateToString(Date date) {

        DateFormat fmt = new SimpleDateFormat(DATA_PATTERN);
        return fmt.format(date);
    }



    /**
     * 转换为 "HH:mm";
     * 时区为GMT8
     * @param date
     * @return
     */
    public static String dateToDayTime(Date date) {

        DateFormat fmt = new SimpleDateFormat(TIME_PATTERN);
        fmt.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        return fmt.format(date);
    }


    /**
     * 转换为形如"YYYY-MM-dd" 的字符串
     *
     * @param date
     * @return
     */
    public static String dateToDay(Date date) {

        DateFormat fmt = new SimpleDateFormat(DATA_DAY_PATTERN);

        return fmt.format(date);

    }

    /**
     * 判断两个日期 的年月日是否相同
     * 默认时区 GMT+8
     * @param d1
     * @param d2
     * @return
     */
    public static boolean sameDate(Date d1, Date d2) {
        if (null == d1 || null == d2)
            return false;
        //return getOnlyDate(d1).equals(getOnlyDate(d2));
        Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        cal1.setTime(d1);
        Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        cal2.setTime(d2);


        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }


//    public static Date DateToGTU0(Date date) {
//
//
//        String s = dateToString(date);
//
//        DateFormat fmt = new SimpleDateFormat(DATA_PATTERN);
////        fmt.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//
//        try {
//            Date parse = fmt.parse(s);
//
//            return parse;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }


    public static void main(String[] args) {


        //Date 是和时区不相关的，依赖的是全局的时区对象

}

}
