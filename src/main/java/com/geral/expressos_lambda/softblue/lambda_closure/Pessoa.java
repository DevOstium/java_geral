package com.geral.expressos_lambda.softblue.lambda_closure;


public class Pessoa {

	private int idade;

	public Pessoa(int idade) {
		this.idade = idade;
	}

	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}

	public double calcularFator(final double ajuste2) {
		final double ajuste = 0.2;
		
		//Closure = acessar uma variavel de fora da expressao Lambda
		FactorCalculator calc = () -> idade * 10 / 2 * ajuste2;
		
		return calc.calculate();
	}
}
