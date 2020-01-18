package com.geral.fox.datas.periodo.java8;


import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class getWeekOfMonth {

	public static void main(String[] args) {

		String p_dateFrom = "201706180000";
		String p_dateTo   = "201706242359";
		
		LocalDateTime now = LocalDateTime.now();
		
		System.out.println(now);
		
		LocalDateTime dateFrom = LocalDateTime.parse(p_dateFrom, DateTimeFormatter.ofPattern("yyyMMddHHmm")); // 2017-06-18T00:00
		LocalDateTime dateTo   = LocalDateTime.parse(p_dateTo, DateTimeFormatter.ofPattern("yyyyMMddHHmm")); // 2017-06-24T23:59
		
		System.out.println(dateFrom);
		System.out.println(dateTo);

		
		LocalDateTime firstDayYear  = dateFrom.with(firstDayOfYear());
		LocalDateTime lastDayYear   = dateTo.with(lastDayOfYear());
		System.out.println(firstDayYear);
		System.out.println(lastDayYear);
		
		
	}

}













