package com.geral.expressos_lambda.softblue.sintax;


public class Main {

	public static void main(String[] args) {
		
		/*
		 *  Classe anonima
		Calculator calc = new Calculator() {
			
			@Override
			public void calculate(int x) {
				x = x * x;
				System.out.println(x);
			}
		};
		
			Calculator calc = (e, f) -> e = e * e;
			           calc.calculate(7, 0);
		 */
		
		    Calculator2 calc2 = p -> p * p;
		    System.out.println(calc2.calculate(9));
	}
}
