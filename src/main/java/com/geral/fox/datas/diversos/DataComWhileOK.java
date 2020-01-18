package com.geral.fox.datas.diversos;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataComWhileOK {

	public static void main(String[] args) {
		Date from = dateBegin();
		Date to = dateEnd();
		//System.out.println(from);
		//System.out.println(to);
		
		calc( from, to );

	}

//1490320802492
//1490320742492	

	private static String calc(Date from, Date to) {
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(from);
		calendar.add(Calendar.HOUR_OF_DAY, 23);

		Calendar calendar2 = new GregorianCalendar();
		calendar2.setTime(to);
		
		
		System.out.println("calendar.getTimeInMillis() " + calendar.getTimeInMillis());
		System.out.println("calendar2.getTimeInMillis() " + calendar2.getTimeInMillis());
		
		if(calendar.getTimeInMillis() == calendar2.getTimeInMillis()){
			System.out.println(" Atingi a hora do TO");
		} else{
			System.out.println(" ainda n�o atingi a hora do TO");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		
//		boolean getOut = false;
//		String xAis = "";
//		while(!getOut){
//			String hour = sdf.format(calendar.getTime());
//			xAis +="'"+hour+"',";
//			//System.out.println(xAis);
//			
//			calendar.add(Calendar.HOUR_OF_DAY, 1);
//			System.out.println(" data " + calendar.getTime());
//			
//			if(calendar.getTimeInMillis() == to.getTime()){
//				//System.out.println(" Atingi a hora do TO");
//				getOut = true;
//			} else{
//				//System.out.println(" ainda n�o atingi a hora do TO");
//				getOut = false;
//			}
//		}
		//System.out.println("xAis �: " + "["+ xAis + "]");
		
		
		
		
		String s = "2007-12-03"+"T00:00:00";
		LocalDateTime dateTime1 = LocalDateTime.parse(s);
		
		LocalDateTime dateTime2 = dateTime1.plusHours(23);
		
		Duration duration = Duration.between(dateTime1, dateTime2);
		
		System.out.println(duration.toHours());
		
		return null;
	}

	public static Date dateBegin(){
		Calendar c1 = new GregorianCalendar(); 
		c1.set(2017, Calendar.MARCH, 23 , 0 , 0 );
		Date dt1 = c1.getTime();
		return dt1;
	}
	public static Date dateEnd(){
		Calendar c2 = new GregorianCalendar(); 
		c2.set(2017, Calendar.MARCH, 23 , 23 , 0  );
		Date dt2 = c2.getTime();
		return dt2;
	}
	
	
	
	
}
