package cc.siriuscloud.xiaoy.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.SimpleFormatter;

public class MyStringUtils {

	private static final String DATA_PATTERN="YYYY-MM-dd HH:mm:ss";

	private static final String TIME_PATTERN="HH:mm";



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


	public static String DateToDayTime(Date date){

		DateFormat fmt =new SimpleDateFormat(TIME_PATTERN);

		return fmt.format(date);
	}


    public static String DateToString(Date date){

        DateFormat fmt =new SimpleDateFormat(DATA_PATTERN);

        return fmt.format(date);
    }





	
	public static void main(String[] args) {
		
		System.out.println(MyStringUtils.String2Md5("sirius"));
	}
	
}
