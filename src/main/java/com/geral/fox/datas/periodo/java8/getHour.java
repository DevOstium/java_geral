package com.geral.fox.datas.periodo.java8;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class getHour {

	public static void main(String[] args) {

		String strFilter = "2017101608";
		LocalDateTime dtFilter = LocalDateTime.parse(strFilter, DateTimeFormatter.ofPattern("yyyyMMddHH"));

		System.out.println(dtFilter);

		LocalDateTime dtFrom = dtFilter.with(LocalTime.MIN);
		LocalDateTime dtTo = dtFilter.with(LocalTime.MAX);

		System.out.println(dtFrom);
		System.out.println(dtTo);

		boolean getOut = true;

		while (getOut) {

			System.out.println("before add one hour: " + dtFrom);
			dtFrom = dtFrom.plusHours(1);
			System.out.println("After add on hour: " + dtFrom);

			Duration interval = Duration.between(dtFrom, dtTo);

			System.out.println(interval.toHours());
			System.out.println(interval.isZero());

			if (interval.toHours() <= 0) {
				getOut = false;
			}

		}

	}

}
