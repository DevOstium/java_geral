package com.geral.fox.datas.diversos;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;



public class GetIntervaloThirty {

	public static void main(String[] args) {

		//String monhtly       =  "MONTHLY";
		//String daily         =  "DAILY";
		//String weekly        =  "WEEKLY";
	    //String week_of_year  =  "WEEK_OF_YEAR";
		String yearly          =  "WEEKLY";
	    
		String dateFromStr          =   "20170402 00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH");
		LocalDateTime dateFrom = LocalDateTime.parse(dateFromStr, formatter);
		Date dateT = new Date();

		Date dateF =  Date.from(dateFrom.atZone(ZoneId.systemDefault()).toInstant());
		if(yearly.equals("YEARLY")){
			LocalDateTime lasteDayMOnth =  dateFrom.with(TemporalAdjusters.lastDayOfYear()).with(LocalTime.MAX);
			              dateT         =  Date.from(lasteDayMOnth.atZone(ZoneId.systemDefault()).toInstant());
		}
		if(yearly.equals("MONTHLY")){
			LocalDateTime lasteDayMOnth =  dateFrom.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
			              dateT         =  Date.from(lasteDayMOnth.atZone(ZoneId.systemDefault()).toInstant());
		}
		if(yearly.equals("DAILY")){
			LocalDateTime lasteDayMOnth =  dateFrom.with(LocalTime.MAX);
			              dateT         =  Date.from(lasteDayMOnth.atZone(ZoneId.systemDefault()).toInstant());
		}
		if(yearly.equals("WEEKLY")){
			LocalDateTime lasteDayMOnth =  dateFrom.plusDays(7).with(LocalTime.MAX);
			              dateT         =  Date.from(lasteDayMOnth.atZone(ZoneId.systemDefault()).toInstant());
		}
		if(yearly.equals("WEEK_OF_YEAR")){
			LocalDateTime lasteDayMOnth = dateFrom.plusDays(7).with(LocalTime.MAX);
			              dateT         =  Date.from(lasteDayMOnth.atZone(ZoneId.systemDefault()).toInstant());
		}

		System.out.println(dateF);
		System.out.println(dateT);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyMMdd");
		String work_id_begin = sdf.format(dateF);
		String work_id_end = sdf.format(dateT);
		
		System.out.println(work_id_begin+"00");
		System.out.println(work_id_end+"23");
		
	}

}
