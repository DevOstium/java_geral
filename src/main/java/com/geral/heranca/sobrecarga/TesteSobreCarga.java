package com.geral.heranca.sobrecarga;

public class TesteSobreCarga {

	public static void main(String[] args) {

		CadastroPessoa cad = new CadastroPessoa();
	
		Pessoa pessoa = new Pessoa("Fagner", 22);

		cad.cadastrar(pessoa);
	   
		cad.cadastrar("Marai", 33);
		
		
	}

}
