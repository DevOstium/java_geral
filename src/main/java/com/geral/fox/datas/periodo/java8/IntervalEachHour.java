package com.geral.fox.datas.periodo.java8;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class IntervalEachHour {


	public static void main(String[] args) {
		
		String update_time_each = "24hours";
		
		
		
		LocalDateTime dt_to = LocalDateTime.now();
		System.out.println(dt_to.with(LocalTime.MAX).truncatedTo(ChronoUnit.MINUTES));
		
		System.out.println(dt_to.with(LocalTime.MIN).truncatedTo(ChronoUnit.MINUTES));
		
		
	}

}
