package com.geral.expressos_lambda;

import java.util.Arrays;
import java.util.List;

public class ExpressoesLambda {

	public static void main(String[] args) {
		List<Integer> l1 = Arrays.asList(new Integer(1));
		List<Integer> l2 = Arrays.asList(1,2,3,4);
		
		//l2.forEach(  numero -> System.out.println("Numero : " + numero) );
		//l2.removeIf( item -> item % 2 == 0 );
	
		l2.forEach(System.out::println );
		
	
	}
	
}
