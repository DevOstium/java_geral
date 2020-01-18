package com.geral.heranca.sobreposicao;

import java.util.Date;

public class ProdutoPerecivel extends Produto {

	protected Date datavalidade;

	public void identificar() {
		
		// Com o super eu acesso o method da superclasse, e executo ela
		super.identificar();

		System.out.println("Minhda data de validade = " + this.datavalidade);
	}
	
}
