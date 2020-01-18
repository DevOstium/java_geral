package com.geral.fox.datas.diversos;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DadasFormatdas {

	public static void main(String[] args) throws ParseException {

		
		Calendar c = Calendar.getInstance();
		
		c.set(2017, Calendar.OCTOBER, 23);
		Date date = c.getTime();
		System.out.println(date);
		
		/* Formatar a Data*/
		DateFormat f =  DateFormat.getDateInstance();
		System.out.println(f.format(date));
		
		/* Formatar a Hora*/
		f = DateFormat.getTimeInstance();
		System.out.println(f.format(date));
		
		/* Formatar a date e Hora*/
	    f = DateFormat.getDateTimeInstance();
		System.out.println(f.format(date));
		
		/* Estilos*/
		f = DateFormat.getDateInstance(DateFormat.FULL);
		System.out.println(f.format(date));
		
		/* Estilos*/
		f = DateFormat.getDateInstance(DateFormat.LONG);
		System.out.println(f.format(date));
		
		/* Estilos*/
		f = DateFormat.getDateInstance(DateFormat.MEDIUM);
		System.out.println(f.format(date));
		
		
		/* Estilos*/
		f = DateFormat.getDateInstance(DateFormat.SHORT);
		System.out.println(f.format(date));
		
		
		/* Converter uma String em Data*/
		Date date2 = f.parse("12/02/1908");
	    DateFormat g = DateFormat.getDateInstance(DateFormat.MEDIUM);
		System.out.println(g.format(date2));
		
		
		/* SimpleDateFormat */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHMM");
		c.set(2017,Calendar.MARCH,24,23,0);
		Date date3 = c.getTime();
		String d = sdf.format(date3);
		System.out.println(d);
		//System.out.println(sdf.parse("20100323"));
		
		
		Calendar c5 = Calendar.getInstance();
		c5.set(2018, Calendar.APRIL, 24);
		System.out.println(" getTimeInMillis � : " + c5.getTime());
		System.out.println(" getTimeInMillis � : " + c5.getTimeInMillis());
		
	
		Date dtChart = new Date(c5.getTimeInMillis());
		System.out.println(" dtChart : " + dtChart);
		
		Calendar cc = new GregorianCalendar();
		cc.setTime(new Date());
		System.out.println(cc.getTime());
	

		DateFormat form =  DateFormat.getDateInstance();
		Date reque = form.parse("12/02/1908 23:23");
	    DateFormat resu = DateFormat.getDateInstance(DateFormat.DEFAULT);
		System.out.println(resu.format(reque));	
	
		LocalDateTime x = LocalDateTime.now();
        System.out.println(x.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));//Shows Day and Date.

        System.out.println(x.format(DateTimeFormatter.ofPattern("EE")));//Short Form
        System.out.println(x.format(DateTimeFormatter.ofPattern("EEEE")));//Long Form
        System.out.println(x.format(DateTimeFormatter.ofPattern("a")));//Long Form
        System.out.println(x.format(DateTimeFormatter.ofPattern("d")));//Long Form
        System.out.println(x.format(DateTimeFormatter.ofPattern("h")));//Long Form
        System.out.println(x.format(DateTimeFormatter.ofPattern("H")));//Long Form
        System.out.println(x.format(DateTimeFormatter.ofPattern("M")));//Long Form

		
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, Calendar.MARCH, 11);
        Date v_day = calendar.getTime();
        System.out.println(new SimpleDateFormat("EE", Locale.ENGLISH).format(v_day.getTime()));
        System.out.println(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(v_day.getTime()));
        System.out.println(new SimpleDateFormat("E", Locale.ENGLISH).format(v_day.getTime()));
        System.out.println(new SimpleDateFormat("MMM", Locale.ENGLISH).format(v_day.getTime()));
        System.out.println(new SimpleDateFormat("MMMM", Locale.ENGLISH).format(v_day.getTime()));
        System.out.println(new SimpleDateFormat("yyyy", Locale.ENGLISH).format(v_day.getTime()));
        System.out.println(new SimpleDateFormat("ww", Locale.ENGLISH).format(v_day.getTime()));

		
	}
	
	
	

}
