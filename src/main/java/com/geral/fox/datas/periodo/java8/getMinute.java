package com.geral.fox.datas.periodo.java8;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class getMinute {

	public static void main(String[] args) {

		String dateFrom = "20170601081523";  // a quantide de caractere aqui tem que ser a mesma do DateTimeFormatter()
		String dateTo   = "20170630180000";
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		
		LocalDateTime from = LocalDateTime.parse(dateFrom, ofPattern);
		
		System.out.println(from);

		System.out.println(LocalDateTime.of(2017, 06, 12, 8, 25, 32, 375));
		
		
		String input = "2017-05-17T00:31:16.227";
		LocalDateTime ldt = LocalDateTime.parse( input );
		System.out.println(ldt);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
