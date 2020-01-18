package com.geral.fox.datas.diversos;


import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class IntervaloDatas {

	public static void main(String[] args) throws ParseException {

		  LocalDate today = LocalDate.now();

	        LocalDate monday = today.minusWeeks(1).with(previousOrSame(MONDAY));
	        LocalDate sunday = today.with(nextOrSame(SUNDAY));
	        
	        System.out.println(monday);
		
		
	        LocalDate todayStop       = LocalDate.now();
	        LocalDate sundayStop      = todayStop.plusDays(0);
	        //LocalDate sundayStop      = todayStop.with(nextOrSame(SUNDAY));
	        DateTimeFormatter fStop   = DateTimeFormatter.ofPattern("yyyyMMdd");
	        SimpleDateFormat sdfStop  = new SimpleDateFormat("yyyyMMdd");
	        String parseStop          = sundayStop.format(fStop);
			Date stop                 = sdfStop.parse(parseStop);
			
			System.out.println(stop);
	        
	        

			String dateString = "2017-11-14 23:59:59";
			LocalDateTime todayString = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			System.out.println(todayString.format(DateTimeFormatter.ISO_WEEK_DATE).substring(0, 8).replace("-", ""));

			System.out.println(todayString.with(firstDayOfYear()).with(LocalTime.MIN));
		    System.out.println(todayString.with(lastDayOfYear()).with(LocalTime.MAX) );
			
			LocalDateTime ldFrom         = todayString.minusWeeks(1).with(nextOrSame(DayOfWeek.MONDAY)).with(LocalTime.MIN);
			LocalDateTime ldToTruncated  = todayString.with(lastDayOfYear()).with(LocalTime.MAX);
			
			LocalDateTime ldTo           = ldToTruncated.truncatedTo(ChronoUnit.SECONDS);
			
			System.out.println(ldTo.format(DateTimeFormatter.ISO_WEEK_DATE).substring(0, 8).replace("-", ""));
			
	}

}
