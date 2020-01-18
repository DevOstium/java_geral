package com.geral.heranca.sobreposicao;

import java.util.Date;

public class TesteSobreposicao {

	public static void main(String[] args) {

		ProdutoPerecivel p = new ProdutoPerecivel();
		p.descricao    = "Leite";
		p.datavalidade = new Date();
		
		p.identificar();
		
	}

}
