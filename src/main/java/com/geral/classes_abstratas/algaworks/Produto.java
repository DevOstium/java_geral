package com.geral.classes_abstratas.algaworks;

public abstract class Produto {

	private String categoria;
	
	public abstract void imprimirDescricao();
	
	protected abstract int getEstoque();
	
	public String getCategoria(String cat) {
		return this.categoria = cat;
	}
	
}
