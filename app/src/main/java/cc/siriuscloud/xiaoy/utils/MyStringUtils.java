package cc.siriuscloud.xiaoy.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.logging.SimpleFormatter;

public class MyStringUtils {

	private static final String DATA_PATTERN="YYYY-MM-dd HH:mm:ss";

	private static final String TIME_PATTERN="HH:mm";

	private static final String DATA_DAY_PATTERN="YYYY-MM-dd";





	public static String String2Md5(String value){
		
		
		try {
			MessageDigest digest=MessageDigest.getInstance("MD5");
			
			byte[] digest2 = digest.digest(value.getBytes());
			
			
			BigInteger integer=new BigInteger(1,digest2);
			
			//前置长度补充
			String md5=integer.toString(16);
			while(md5.length()<32){
				md5='0'+md5;
			}
			return md5;
		
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
			
			return value;
		}
		
	}
	
	
	public static String getUUID(){
		
		return UUID.randomUUID().toString().replaceAll("-", "");
		
	}


	/**
	 * "YYYY-MM-dd HH:mm:ss"
	 * @param date
	 * @return
	 */
	public static String DateToDayTime(Date date){

		DateFormat fmt =new SimpleDateFormat(TIME_PATTERN);

		return fmt.format(date);
	}

	/**
	 * "HH:mm"
	 * @param date
	 * @return
	 */
    public static String DateToString(Date date){

        DateFormat fmt =new SimpleDateFormat(DATA_PATTERN);

        return fmt.format(date);
    }


	/**
	 * "YYYY-MM-dd"
	 * @param date
	 * @return
	 */
	public static String DateToDay(Date date){

		DateFormat fmt =new SimpleDateFormat(DATA_DAY_PATTERN);

		return fmt.format(date);

	}



	public static boolean sameDate(Date d1, Date d2) {
		if(null == d1 || null == d2)
			return false;
		//return getOnlyDate(d1).equals(getOnlyDate(d2));
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(d1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(d2);
		return  cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
				cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
	}



	public static void main(String[] args) {

		System.out.println(MyStringUtils.String2Md5("sirius"));
	}

}
