package com.geral.arrays;

public class CriarArrayTeste2 {

	
	String[] firstNames = {"Fagner", "Lira"};
	String[] lastNames  = new String[firstNames.length];
	
	void printNomes() {
		int i = 0;
		System.out.println(firstNames[i]   + "  "+ lastNames[i]);
		i++;
		System.out.println(firstNames[i]   + "  "+ lastNames[i]);
	}
	
	public static void main(String[] args) {

		CriarArrayTeste2 array = new CriarArrayTeste2();
		
		array.printNomes();
		
		System.out.println("*****************");
	
		array.lastNames[0] = "Maria";
		array.lastNames[1] = "Cristina";
		array.printNomes();
		
	}
	
}
