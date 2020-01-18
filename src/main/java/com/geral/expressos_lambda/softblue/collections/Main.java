package com.geral.expressos_lambda.softblue.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		// Essa lista nao pode ser modificada, portando nao posso usar o removeif
		//List<Integer> list = Arrays.asList(4, 5, 2, 1, 0, 3);
		
		List<Integer>   list = new ArrayList<>();
						list.add(4);
						list.add(5);
						list.add(2);
						list.add(1);
						list.add(0);
						list.add(3);
	
		// Ordem DECRESCENTE usar o - (menos)
		//list.sort((x, y) -> - x.compareTo(y)); 
	
		// Odem CRESCENTE
		//list.sort((x, y) ->   x.compareTo(y)); 
		
		//list.replaceAll( e -> e * 2 );
		
		list.removeIf( e -> e % 2 != 0);  // nao usar com o Arrays.aslist()
	
		list.forEach(System.out::println);

	}
}









