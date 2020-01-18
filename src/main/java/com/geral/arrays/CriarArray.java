package com.geral.arrays;

import java.util.stream.Stream;

public class CriarArray {

	
	public static void main(String[] args) {

		String[] array = {"Leite", "Banana", "Carne"};
		Stream.of(array).forEach(System.out::println);
		
		// Classes
		Produto[] arraysProdutos    = new Produto[2];
		
		          arraysProdutos[0] = new Produto("Leite");
		          arraysProdutos[1] = new Produto("Queijo");
		          
		    Stream.of(arraysProdutos).forEach(System.out::print);  
		  
		    
		    
		    int     val    =  23;
		    int[]   notas  =  new int[4];
				    notas[0] = 3;
				    notas[1] = 44;
				    notas[2] = 14;
				    notas[3] = 24;
		    
		    System.out.println("Acesso ao elemento do Array: " + notas[2]);
		    
		    for(int i=0; i < notas.length; i++) {
		    	System.out.println("Posicao i: " + i + " - Notas : " + notas[i]);
		    }
	}
	
}









