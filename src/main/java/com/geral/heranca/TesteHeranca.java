package com.geral.heranca;

public class TesteHeranca {

	public static void main(String[] args) {
		
		Jogador jogador = new Jogador();
		jogador.idade = 12;
		jogador.aindaJoga = false;
		
		System.out.println(jogador.idade);

		Pessoa p = new Tecnico();
		p.nome = "Fagner";
		System.out.println("Acesso a superClass : " + p.nome);
		
	}
}
