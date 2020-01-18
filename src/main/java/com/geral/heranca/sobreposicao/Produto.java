package com.geral.heranca.sobreposicao;

public class Produto {


	protected String descricao;
	
	public void identificar() {
		System.out.println("Teste usando sobrecarga de methods com descricao : " + this.descricao );
	}
	
}
