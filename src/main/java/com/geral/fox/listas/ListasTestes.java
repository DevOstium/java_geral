package com.geral.fox.listas;


import java.util.Arrays;
import java.util.List;

public class ListasTestes {

	public static void main(String[] args) {
	
		Cliente c1 = new Cliente();
		c1.setNome("Fagner Lira");
		
		c1.setLista(Arrays.asList("Fagner", "Maria"));
		List<String> a = c1.getLista();
		
		for(String b : a){
			System.out.println(a);
		}
		
		System.out.println(c1.getNome());
		
	}
	
	public static final class Cliente {
		private String nome;
		private String sobreNome;
		private List<String> lista;

		public Cliente(){}

		public Cliente(String nome, String sobreNome){
			this.nome = nome;
			this.sobreNome = sobreNome;
		}

		public List<String> getLista(){
			return this.lista;
		}
		public void setLista(List<String> lista){
			this.lista = lista;
		}
		public String getNome(){
			return this.nome;
		}
		public void setNome(String nome){
			this.nome = nome;
		}
		
		public String getSobreNome(){
			return this.sobreNome;
		}
		public void setSobreNome(String sobreNome){
			this.sobreNome = sobreNome;
		}
	} // end Class Cliente
	
}


