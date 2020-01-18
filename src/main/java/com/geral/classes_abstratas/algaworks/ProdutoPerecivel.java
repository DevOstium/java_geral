package com.geral.classes_abstratas.algaworks;

public class ProdutoPerecivel extends Produto {

	String descricao;
	String dataValidade;
	
	@Override
	public void imprimirDescricao() {

		System.out.println("Descricao : " + this.descricao + " - DataValidade : " + this.dataValidade);
	}

	@Override
	protected int getEstoque() {
		// TODO Auto-generated method stub
		return 0;
	}

}
