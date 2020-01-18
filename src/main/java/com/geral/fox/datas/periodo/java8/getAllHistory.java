package com.geral.fox.datas.periodo.java8;

import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

//import br.com.foxconn.tdk.config.CustomTDK;
//import br.com.foxconn.tdk.dao.efficiency.feederEfficiency.feederAttrition.FeederAttritionDao;

public class getAllHistory {

	private static final boolean String = false;

	public static void main(String[] args) {

		String dateFilter = "2017103123";
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyyMMddHH");
		
		LocalDateTime periodBegin = LocalDateTime.now();  
		LocalDateTime periodEnd   = LocalDateTime.parse(dateFilter, sdf);
		
		System.out.println("periodBegin first: " + periodBegin);
		System.out.println("periodEnd second : " + periodEnd);
		
		List<String>  keyDates =  new ArrayList<String>();
		List<String>  valueXAix  =  new ArrayList<>();
		
		DateTimeFormatter keyYear       = DateTimeFormatter.ofPattern("yyyy");
		DateTimeFormatter keyMonth      = DateTimeFormatter.ofPattern("MM");
		DateTimeFormatter keyWeek       = DateTimeFormatter.ofPattern("ww");
		DateTimeFormatter keyDay        = DateTimeFormatter.ofPattern("dd");
		DateTimeFormatter keyDateFilter = DateTimeFormatter.ofPattern("yyyyMMddHH", Locale.ENGLISH);
		
		boolean getOut = false;	
		List<String> asHistory = Arrays.asList("year", "month", "week", "day");

		TemporalField   getWeek = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear(); 
		int weekNumberBegin = 1;
		int weekNumberEnd   = 2;
		
		int i = 10;
		for(String s : asHistory) {
			System.out.println("Primeiro : " + s);
			getOut = false;
			
			if(s.equals("year")) {
				periodBegin = periodEnd.minusYears(3).withDayOfYear(1).with(LocalTime.MIN);
			}
			if(s.equals("month")) {
				periodBegin = periodEnd.withDayOfYear(1).with(LocalTime.MIN);
			}
			if(s.equals("week")) {
				periodBegin = periodEnd.minusWeeks(4).with(LocalTime.MIN);
			}
			if(s.equals("day")) {
				periodBegin        = periodEnd.with(previousOrSame(DayOfWeek.SUNDAY)).with(LocalTime.MIN);
				periodEnd          = periodEnd.with(nextOrSame(DayOfWeek.SUNDAY)).with(LocalTime.MAX);
			}
			
			System.out.println("dateFrom  now is: "+ periodBegin);
			System.out.println("periodEnd now is: "+ periodEnd);
			while(!getOut) {	
				
				if(s.equals("year")) {
					keyDates.add(i+++keyDateFilter.format(periodEnd));
					valueXAix.add("Year"+keyYear.format(periodBegin));
					periodBegin = periodBegin.plusYears(1);
				}
				if(s.equals("month")) {
					keyDates.add(i+++keyDateFilter.format(periodEnd));
					valueXAix.add("Month"+keyMonth.format(periodBegin));
					periodBegin = periodBegin.plusMonths(1);
				}
				if(s.equals("week")) {
					keyDates.add(keyDateFilter.format(periodBegin));
					
					valueXAix.add("WK"+keyWeek.format(periodBegin));
					
					System.out.println("WK"+keyWeek.format(periodBegin));
					System.out.println(periodBegin);
					
					periodBegin = periodBegin.plusWeeks(1);
					
					weekNumberBegin  = periodBegin.get(getWeek);
					weekNumberEnd    = periodEnd.get(getWeek);
					

					if(weekNumberBegin == weekNumberEnd) {
						System.out.println("ok");
					}
					
					
					
					
				}
				if(s.equals("day")) {
					keyDates.add(keyDateFilter.format(periodBegin));
					valueXAix.add("Day"+keyDay.format(periodBegin));
					periodBegin = periodBegin.plusDays(1);
				}
				
				Duration interval = Duration.between(periodBegin, periodEnd);
				Duration stop     = Duration.between(periodBegin, LocalDateTime.now().plusDays(1));
				
				
				
			    
			    
	
			    
			    
			    
			    
			    
			    
				//System.out.println(interval.toDays());
				if(interval.toDays() <=0 || stop.toDays() <=0) {
					getOut = true;
				}
			}
		}
		//Map<String,String> mapxAix = CustomTDK.joinTwoLitsOneMap(keyDates, valueXAix);
		//CustomTDK.printMap(mapxAix);
		
		

/* return = [Year2014, Year2015, Year2016
		     ,Month01, Month02, Month03, Month04
		     ,Month05, Month06, Month07, Month08
		     ,Month09, Month10
		     ,WK41, WK42
		     ,Day22, Day23, Day24, Day25, Day26, Day27, Day28]
*/		
		
	}

}
