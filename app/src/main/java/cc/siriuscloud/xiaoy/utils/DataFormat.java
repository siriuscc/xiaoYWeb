package cc.siriuscloud.xiaoy.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DataFormat {

	private static final String TAG="DataFormat";

	private static final String DATA_PATTERN="YYYY-MM-dd HH:mm:ss";


	public static String format(Object obj) {

		if(obj==null){
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
	 * 转换字符串到日期
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		
		SimpleDateFormat sdf=new SimpleDateFormat(DATA_PATTERN);
		String datastr=sdf.format(date);
		
		return datastr;
	}

	/**
	 * 转换日期到字符串
	 * @param string
	 * @return
	 */
	public static Date stringToDate(String string){

		DateFormat fmt =new SimpleDateFormat(DATA_PATTERN);


		try {
			return fmt.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;

		}

	}



	
	
}
