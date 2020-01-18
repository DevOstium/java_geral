package com.geral.classes_abstratas.algaworks;

public class TesteClasseAbstrata {

	public static void main(String[] args) {

		Produto produto = new ProdutoPerecivel();

		ProdutoPerecivel produtoPerecivel = (ProdutoPerecivel) produto;
		produtoPerecivel.descricao     = "Perecivel";
		produtoPerecivel.dataValidade  = "12/12/2019";
		
		produto.imprimirDescricao();
	}

}
