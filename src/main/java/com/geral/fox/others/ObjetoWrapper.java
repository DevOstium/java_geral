package com.geral.fox.others;


import java.util.ArrayList;
import java.util.List;

public class ObjetoWrapper {

	private int id;
	
	private List<Objeto> lista = new ArrayList<>();

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Objeto> getLista() {
		return lista;
	}

	public void setLista(List<Objeto> lista) {
		this.lista = lista;
	}

	
	
}
