package com.geral.fox.datas.diversos;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Datas {

	public static void main(String[] args) {

		
		/* 01 jan de 1970  - Quantidade em millis segundo = 1490218461893*/
		System.out.println(System.currentTimeMillis());
		
		
		
		Date dataAgora = new Date();
		System.out.println(dataAgora);
		
		Date data = new Date(1_000_000_000_000L);
		System.out.println(data);
		
		
		/* Metodos*/
		data.getTime();  // retorna data atual em mille segundos
		data.setTime(1_000_000_000_000L); // usa para definir, inserir uma data em mille segundos
		System.out.println(data.compareTo(dataAgora)); // compara as datas retorna: -1(menor),  0(iguais)  , 1(maior)
		
		
		// Essas duas formas s�o a mesma coisa
		Calendar c2 = new GregorianCalendar();
		Calendar c = Calendar.getInstance();
		
		
		c.set(1980, Calendar.SEPTEMBER, 8 );
		System.out.println(c.getTime());
		
		
		System.out.println(c.get(Calendar.YEAR));		
		System.out.println(c.get(Calendar.MONTH));		
		System.out.println(c.get(Calendar.DAY_OF_MONTH) );		
		
		c.set(Calendar.YEAR, 1972);
		c.set(Calendar.MONTH, Calendar.MARCH );
		c.set(Calendar.DAY_OF_MONTH, 12);
		
		System.out.println(c.getTime());
		
		/* Limpar campos*/		
		c.clear(Calendar.MINUTE);		
		c.clear(Calendar.SECOND);
		System.out.println(c.getTime());
	
	
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.YEAR, 1);
		System.out.println(c.getTime());
		
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.add(Calendar.YEAR, -1);
		System.out.println(c.getTime());
		
		System.out.println("  *****   ");
	
		
		
		c.set(2017, Calendar.MARCH, 20 );
		System.out.println(c.getTime());
		//Aumenta o dia dentro do mesmo mes, mas pode ser outros, ano, mes , dias ect...
		c.roll(Calendar.DAY_OF_MONTH,  15);
		System.out.println(c.getTime());
		
	
		
		/*  Sauda��o com Bom dia, Boa tarde, Boa noite*/
		
		Calendar c4 =  Calendar.getInstance();
		int hora = c4.get(Calendar.HOUR_OF_DAY);
		System.out.println(hora);
		if(hora <= 12) {
			System.out.println("Bom dia");
		} else if ( hora > 12  && hora < 18 ) {
			System.out.println(" Boa tarde");
		} else {
			System.out.println("Boa noite");
		}
		
		
		
		
		
		
		c.set(2017, 03, 22, 00, 00, 00);
		Date dt1 = c.getTime(); 
		
		System.out.println(" c.getTimeInMillis() " + c.getTimeInMillis());
		
		System.out.println("dt1:" +  dt1.getTime());

		Date retorno = dateSegund();
		System.out.println("dt2:" + dateSegund().getTime());
		
		System.out.println(dt1.compareTo(retorno));
		
		if(dt1.compareTo(retorno) == 0){
			System.out.println("sim a data � a mesma");
		} else{
			System.out.println(" ainda � maior");
		}
		
		
				boolean getOut = false;
				String  s ="";
				int count = 0;
				int result = 10;
				while(!getOut){
					
					s += count++;
					System.out.println(" valor de s" + s);
					c.set(2017, 03, 23, 07, 00, 00);
					c.add(Calendar.HOUR_OF_DAY, 0);
					if(dateSegund().getTime() == c.getTimeInMillis()){
						System.out.println(" sim ");
						getOut = true;
					}
				}
		
		
		
	}// end main
	
	
	public static Date dateSegund (){
		Calendar cc = new GregorianCalendar(); 
		cc.set(2017, 03, 23, 00, 00, 1);
		Date dt2 = cc.getTime();
		return dt2;
	}
		

}
