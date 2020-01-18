package com.geral.generics.pesquisa;

public class Pessoa {

	private String nome;
	private Double salario;

	protected Pessoa(String nome, Double salario) {
		this.nome = nome;
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	@Override
	public String toString() {
		return "Nome : " + this.nome ;
	}

}
