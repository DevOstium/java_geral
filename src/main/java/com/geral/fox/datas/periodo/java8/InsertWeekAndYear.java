package com.geral.fox.datas.periodo.java8;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class InsertWeekAndYear {
	public static void main(String[] args) {

		int week = 36;
		int year = 2017;
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		LocalDateTime ldt = LocalDateTime.now().withYear(year).with(weekFields.weekOfYear(), week)
				.with(weekFields.dayOfWeek(), 1);
		System.out.println(ldt);

	}
}
