package com.geral.interfaces.exemplo.impressao.impressoras;

import com.geral.interfaces.exemplo.impressao.Impressora;
import com.geral.interfaces.exemplo.impressao.Imprimivel;

public class ImpressoraHP implements Impressora {

	@Override
	public void imprimir(Imprimivel imprimivel) {
		
		System.out.println("Chamando a classe ImpressoraHP ");
		
		System.out.println(imprimivel.getCabecalhoPagina());
		System.out.println("------------------------------------------------");
		System.out.println(imprimivel.getCorpoPagina());
		System.out.println();
		System.out.println("================================================");
		System.out.println("......................HP........................");
		System.out.println("================================================");
	}

}
