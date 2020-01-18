package com.geral.fox.datas.diversos;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatasCalendar {

	public static void main(String[] args) {
		
		Date from = dateBegin();
		Date to = dateEnd();
		System.out.println(from);
		System.out.println(to);
		
		calc( from, to );

	}
	
	private static String calc(Date from, Date to) {
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(from);
		
		boolean getOut = false;
		int count = 0;
		String xAis = "";
		while(!getOut){
			count++;
			xAis +="'"+count+"',";
			//System.out.println(xAis);
			
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			if(calendar.getTimeInMillis() == to.getTime()){
				System.out.println(" Atingi a hora do TO");
				getOut = true;
			} else{
				System.out.println(" ainda n�o atingi a hora do TO");
				getOut = false;
			}
		}
		System.out.println("xAis �: " + "['"+0+"'," + xAis + "]");
		
		return null;
	}

	public static Date dateBegin(){
		Calendar c1 = new GregorianCalendar(); 
		c1.set(2017, 03, 23, 0, 0 );
		Date dt2 = c1.getTime();
		return dt2;
	}
	public static Date dateEnd(){
		Calendar c2 = new GregorianCalendar(); 
		c2.set(2017, 03, 25, 23, 0 );
		Date dt2 = c2.getTime();
		return dt2;
	}
	
	
	
	
}
