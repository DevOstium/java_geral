package com.geral.fox.datas.diversos;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FormatDate2 {

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {

		String s = "2017031423";
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");
		Date date =  sdf.parse(s);
		//System.out.println(date);
	    
	    Calendar c = new GregorianCalendar();
	    c.setTime(date);
	    //System.out.println(c.getTime());

	    Date d = new Date();
	    SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhh");
	    String l = f.format(d);
	    //System.out.println(l);
	    Date dt =  f.parse(l);

	    c.setTime(dt);
	   // System.out.println(c.getTime());
	    
	    
	    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHH");
	    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    
	    Calendar now = new GregorianCalendar();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        String k = sdf2.format(now.getTime());
        Date p = sdf2.parse(k);
        System.out.println(p);
        
        String p_from = sdf3.format(now.getTime());
        System.out.println("p_from "+ p_from);

        
        now.set(Calendar.HOUR_OF_DAY, 23);
        String q = sdf2.format(now.getTime());
        Date r = sdf2.parse(q);
        System.out.println(r);
	    
        
        String p_to = sdf3.format(now.getTime());
        System.out.println(" p_to " + p_to);
        
	    
	}

	

}
