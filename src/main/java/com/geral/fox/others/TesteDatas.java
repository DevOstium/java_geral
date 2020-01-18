package com.geral.fox.others;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

//import br.com.foxconn.tdk.model.fpy.FpyChartFilter;

public class TesteDatas {

	public static void main(String[] args) throws ParseException {
	//	FpyChartFilter f = new FpyChartFilter();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
		Calendar c1 = new GregorianCalendar(2017,3,22,00,00,00);
		//c1.add(Calendar.HOUR_OF_DAY, -2);

		//f.setFrom(c1.getTime());
		
		System.out.println(" a " + sdf.format(c1.getTime()));
		//System.out.println(" b " + f.getFrom());
		
		
		Calendar c2 = new GregorianCalendar(2017,3,23,23,59,00);
		//c2.add(Calendar.DAY_OF_MONTH, 0);
		c2.get(Calendar.DAY_OF_MONTH);
		//f.setTo(c2.getTime());
		
		System.out.println(" c " + sdf.format(c2.getTime()));
		//System.out.println(" d " + f.getTo());


		System.out.println("***");
		
//		boolean t = false;
//		String s = "";
//		int count = 0;
//		while (!t){
//			count++;
//			s += count++;
//			System.out.println(s);
//		
//			c2.add(Calendar.HOUR_OF_DAY, 1);
//			if(f.getTo().getTime() == c2.getTimeInMillis() ){
//				System.out.println(" while ok ...");
//				t =true;
//			}
//		}//while

	
	
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
		Calendar calendar = new GregorianCalendar(2017,3,8,16,11,56);

		int year       = calendar.get(Calendar.YEAR);
		int month      = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		int dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK);
		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		int weekOfMonth= calendar.get(Calendar.WEEK_OF_MONTH);

		int hour       = calendar.get(Calendar.HOUR);        // 12 hour clock
		int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
		int minute     = calendar.get(Calendar.MINUTE);
		int second     = calendar.get(Calendar.SECOND);
		int millisecond= calendar.get(Calendar.MILLISECOND);

		System.out.println(sdf.format(calendar.getTime()));

		System.out.println("year \t\t: " + year);
		System.out.println("month \t\t: " + month);
		System.out.println("dayOfMonth \t: " + dayOfMonth);
		System.out.println("dayOfWeek \t: " + dayOfWeek);
		System.out.println("weekOfYear \t: " + weekOfYear);
		System.out.println("weekOfMonth \t: " + weekOfMonth);

		System.out.println("hour \t\t: " + hour);
		System.out.println("hourOfDay \t: " + hourOfDay);
		System.out.println("minute \t\t: " + minute);
		System.out.println("second \t\t: " + second);
		System.out.println("millisecond \t: " + millisecond);
	
	
	}

}




//Date dateFrom = new SimpleDateFormat("yyyy-MM-dd hh").parse("2017-03-22 00");
//c.setTime(dateFrom);
//f.setFrom(c.getTime());
//System.out.println(c.getTime());
//
//Date dateTo = new SimpleDateFormat("yyyy-MM-dd hh").parse("2017-03-23 23");
//c.setTime(dateTo);
//f.setTo(c.getTime());
//System.out.println(c.getTime());

