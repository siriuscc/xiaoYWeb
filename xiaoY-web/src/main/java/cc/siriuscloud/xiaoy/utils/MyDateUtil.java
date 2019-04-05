package cc.siriuscloud.xiaoy.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MyDateUtil {

    private static final String TAG = "MyDateUtil";

    private static final String DATA_PATTERN = "YYYY-MM-dd HH:mm:ss";

    private static final String TIME_PATTERN = "HH:mm";

    private static final String DATA_DAY_PATTERN = "YYYY-MM-dd";


    public static String format(Object obj) {

        if (obj == null) {
            return null;
        }

        if (obj instanceof Date) {
            return dateToString((Date) obj);
        }

        if (obj instanceof Integer) {

            return obj + "";
        }

        return obj + "";
    }



    /**
     * "HH:mm"
     *
     * @param date
     * @return
     */
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
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d2);
        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }


    public static void main(String[] args) {


//        System.out.println(dateToString(Calendar.getInstance().getTime()));
//        System.out.println(dateToString(Calendar.getInstance().getTime()));


    }

}
