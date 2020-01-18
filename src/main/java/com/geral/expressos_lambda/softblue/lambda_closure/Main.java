package com.geral.expressos_lambda.softblue.lambda_closure;


public class Main {

	public static void main(String[] args) {
		
		Pessoa   p      = new Pessoa(28);
		
		double   ajuste = 0.2;
		double   fator  = p.calcularFator(ajuste);
		
		System.out.println(fator);
	}
}
