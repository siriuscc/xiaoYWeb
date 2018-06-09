package cc.siriuscloud.xiaoy.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


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
     * 提交上去的时候要设置时区，回来不需要设置时区
     * "HH:mm"
     *
     * @param date
     * @return
     */
    public static String dateGMT8ToString(Date date) {

        DateFormat fmt = new SimpleDateFormat(DATA_PATTERN);
        fmt.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        return fmt.format(date);
    }


    public static String dateToString(Date date) {

        DateFormat fmt = new SimpleDateFormat(DATA_PATTERN);

        return fmt.format(date);
    }



    /**
     * 转换日期到字符串
     *
     * @param string
     * @return
     */
    public static Date stringToDate(String string) {

        DateFormat fmt = new SimpleDateFormat(DATA_PATTERN);

        try {
            return fmt.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * "YYYY-MM-dd HH:mm:ss"
     *
     * @param date
     * @return
     */
    public static String dateToDayTime(Date date) {

        DateFormat fmt = new SimpleDateFormat(TIME_PATTERN);
        fmt.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        return fmt.format(date);
    }


    /**
     * "YYYY-MM-dd"
     *
     * @param date
     * @return
     */
    public static String dateToDay(Date date) {

        DateFormat fmt = new SimpleDateFormat(DATA_DAY_PATTERN);

        return fmt.format(date);

    }


    public static boolean sameDate(Date d1, Date d2) {
        if (null == d1 || null == d2)
            return false;
        //return getOnlyDate(d1).equals(getOnlyDate(d2));
        Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        cal1.setTime(d1);
        Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        cal2.setTime(d2);


//
//        Log.d(TAG,"cal1 ......."+MyDateUtil.dateGMT8ToString(d1)+" | "+cal1.get(Calendar.ERA)+" | "+cal1.get(Calendar.YEAR)+" | "+cal1.get(Calendar.DAY_OF_YEAR));
//        Log.d(TAG,"cal2......."+MyDateUtil.dateGMT8ToString(d2)+" | "+cal2.get(Calendar.ERA)+" | "+cal2.get(Calendar.YEAR)+" | "+cal2.get(Calendar.DAY_OF_YEAR));
//        Log.d(TAG,"\n###############################\n");


        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }


    public static Date DateToGTU0(Date date) {


        String s = dateToString(date);

        DateFormat fmt = new SimpleDateFormat(DATA_PATTERN);
//        fmt.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        try {
            Date parse = fmt.parse(s);

            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;


    }


    public static void main(String[] args) {


        System.out.println(System.currentTimeMillis());

        Date date = new Date();
        System.out.println(date.getTime());


        //Date 是和时区不相关的，依赖的是全局的时区对象

}

}
