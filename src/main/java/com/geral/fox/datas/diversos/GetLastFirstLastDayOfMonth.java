package com.geral.fox.datas.diversos;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

//https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
public class GetLastFirstLastDayOfMonth {

	public static void main(String[] args) throws ParseException {

		    LocalDate today = LocalDate.now();
	        LocalDate firstDayMonth = today.withDayOfMonth(1);
	        LocalDate lastDayMonth = today.withDayOfMonth(today.getMonth().maxLength());

	        System.out.println(firstDayMonth);
	        System.out.println(lastDayMonth);
	        
	        DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("yyyyMMdd");
	        SimpleDateFormat sdf7 = new SimpleDateFormat("yyyyMMdd");

	        String parsefrom = firstDayMonth.format(formatador);
	        String parseto   = lastDayMonth.format(formatador);
	       
			Date from =  sdf7.parse(parsefrom);
			//f.setFrom(from);
			System.out.println(" dateFrom " + from);

			Date to =  sdf7.parse(parseto);
			//f.setTo(to);
			System.out.println(" dateTo " + to);
			
			String p_work_id_begin = firstDayMonth.format(formatador)+"00";
			String p_work_id_end   = lastDayMonth.format(formatador)+"23";
			
			//f.setWork_id_begin(p_work_id_begin);
			//f.setWork_id_end(p_work_id_end);
			
			System.out.println(" p_work_id_begin " + p_work_id_begin);
			System.out.println(" p_work_id_end " + p_work_id_end);
			
			
			 Calendar calendar = new GregorianCalendar();
			 SimpleDateFormat sdfMonthly = new SimpleDateFormat("MMMM", Locale.ENGLISH);
			 calendar.setTime(new Date());
			 System.out.println(sdfMonthly.format(calendar.getTime()));
	        
	        
			 LocalDate todayStop = LocalDate.now();
			 LocalDate lastDayMonthStop = todayStop.withDayOfMonth(todayStop.getMonth().maxLength());
	         DateTimeFormatter fStop =  DateTimeFormatter.ofPattern("yyyyMMdd");
	         SimpleDateFormat sdfStop = new SimpleDateFormat("yyyyMMdd");
	         String parseStop   = lastDayMonthStop.format(fStop);
	         Date stop =  sdfStop.parse(parseStop);
	         System.out.println(stop.getTime());
			 
	}

}


















