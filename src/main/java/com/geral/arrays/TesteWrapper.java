package com.geral.arrays;

public class TesteWrapper {

	
	public static void main(String[] args) {
		
		// Tpos primitivos : byte, short, int    ,   long, float, double, char
		// Classes         : Byte, Short, Integer,   Long, Float, Double, Character

		int x = 5;
		
		//Wrapper
		Integer i = new Integer(3);
	
		byte y = i.byteValue();
		
		String valor = "15.5";
		
		Float f = new Float(valor);
		System.out.println(f + 4.4);

		int parseInt = Integer.parseInt("33");
		System.out.println(parseInt);
		
		byte parseByte = Byte.parseByte("33");
		System.out.println(parseByte);
		
		// Boxer = O java faz uma conversao de forma automatica
		Integer boxer = 10;
		
		int uboxing = boxer;
		
	}
}








