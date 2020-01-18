package com.geral.fox.generic;


import java.util.List;

public class Lista {

	public static void pro(List<?> el) {
		
		for (Object o : el) {
			System.out.println(o);
		}
		
	}
	
}
