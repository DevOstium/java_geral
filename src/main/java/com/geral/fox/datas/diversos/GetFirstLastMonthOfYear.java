package com.geral.fox.datas.diversos;


import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


//http://www.mscharhag.com/java/java-8-date-time-api
//https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
public class GetFirstLastMonthOfYear {

	public static void main(String[] args) throws ParseException {

		    LocalDate today = LocalDate.now();
		    LocalDate firstDayMonthOfYear = today.withDayOfYear(1);
		    LocalDate lastDayMonthOfYear =  today.with(lastDayOfYear());
		    
	        System.out.println(firstDayMonthOfYear);
	        System.out.println(lastDayMonthOfYear);
	        
	        DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("yyyyMMdd");
	        SimpleDateFormat sdf7 = new SimpleDateFormat("yyyyMM");

	        String parsefrom = firstDayMonthOfYear.format(formatador);
	        String parseto   = lastDayMonthOfYear.format(formatador);
	       
	        
	        Calendar calendar = new GregorianCalendar();
			Date from =  sdf7.parse(parsefrom);
			//f.setFrom(from);
			calendar.setTime(from);
			System.out.println(" calendar " + calendar.getTimeInMillis());
			System.out.println(" dateFrom " + from);

			
			
			
			
			
			
			Date to =  sdf7.parse(parseto);
			//f.setTo(to);
			System.out.println(" dateTo " + to);
			
			String p_work_id_begin = firstDayMonthOfYear.format(formatador)+"00";
			String p_work_id_end   = lastDayMonthOfYear.format(formatador)+"23";
			
			//f.setWork_id_begin(p_work_id_begin);
			//f.setWork_id_end(p_work_id_end);
			
			System.out.println(" p_work_id_begin " + p_work_id_begin);
			System.out.println(" p_work_id_end " + p_work_id_end);

	      

			
			
			 LocalDate todayStop = LocalDate.now();
			 LocalDate lastDayMonthOfYearStop =  today.with(lastDayOfYear());
			 DateTimeFormatter fStop =  DateTimeFormatter.ofPattern("yyyyMMdd");
	         SimpleDateFormat sdfStop = new SimpleDateFormat("yyyyMMdd");
	         String parseStop   = lastDayMonthOfYearStop.format(fStop);
	         Date stop =  sdfStop.parse(parseStop);
	         System.out.println(stop);


	

	       
			 
	}

}


















