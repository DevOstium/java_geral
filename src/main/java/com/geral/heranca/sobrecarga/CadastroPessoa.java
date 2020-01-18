package com.geral.heranca.sobrecarga;

public class CadastroPessoa extends Pessoa {

	// Usar o mesmo nome de um metho mas com parametros diferentes = SobreCarga
	
	public void cadastrar(Pessoa pessoa) {
		salvar(pessoa.getNome(), pessoa.getIdade());
	}
	
	public void cadastrar(String nome, int idade) {
		salvar(nome, idade);
	}
	
	private void salvar(String nome, int idade) {
		System.out.println("Salvando Nome: " + nome + " - Idade : " + idade);
	}

}
