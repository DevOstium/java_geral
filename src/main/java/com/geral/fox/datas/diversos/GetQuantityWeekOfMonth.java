package com.geral.fox.datas.diversos;


import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


//https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
public class GetQuantityWeekOfMonth {

	public static void main(String[] args) throws ParseException {
		
		LocalDate today = LocalDate.now();
		
		
		WeekFields weekFields   = WeekFields.of(Locale.getDefault()); 
		int firstWeekOfYear     = weekFields.getMinimalDaysInFirstWeek();
		int lastWeekOfYear      = today.get(weekFields.weekOfWeekBasedYear());
		
		System.out.println("Today: " + today);
		System.out.println(" firstWeekOfYear " + firstWeekOfYear);
		System.out.println(" lastWeekOfYear " + lastWeekOfYear);
		
	    LocalDate firstDayMonthOfYear = today.withDayOfYear(1);
	    LocalDate lastDayMonthOfYear =  today.with(lastDayOfYear());
	    
        System.out.println("firstDayMonthOfYear " + firstDayMonthOfYear);
        System.out.println(" lastDayMonthOfYear " + lastDayMonthOfYear);
        
        Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
        
		SimpleDateFormat sdfWhile = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat sdfWeek = new SimpleDateFormat("yyyyMMdd");
		
		DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    	String dateFrom    = sdfWeek.format(calendar.getTime());
    	System.out.println(" dateFrom " + dateFrom );
		
    	LocalDate   date        = LocalDate.parse(dateFrom, formatter);
		WeekFields  week        = WeekFields.of(Locale.getDefault()); 
		Integer     weekOfYear  = date.get(week.weekOfWeekBasedYear());
		
		System.out.println(" weekOfYear " +  weekOfYear.toString());
		
		String result = sdfWhile.format(calendar.getTime())+weekOfYear.toString();
		System.out.println(" result " + result);
		
		
        
		
	}
	
}










