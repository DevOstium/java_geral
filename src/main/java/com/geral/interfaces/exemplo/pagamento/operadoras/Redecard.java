package com.geral.interfaces.exemplo.pagamento.operadoras;

import com.geral.interfaces.exemplo.pagamento.Autorizavel;
import com.geral.interfaces.exemplo.pagamento.Cartao;
import com.geral.interfaces.exemplo.pagamento.Operadora;

public class Redecard implements Operadora {

	@Override
	public boolean autorizar(Autorizavel autorizavel, Cartao cartao) {
		 boolean r = cartao.getNumeroCartao().startsWith("456") && autorizavel.getValorTotal() < 200 ? true : false;
		 return r;
	}

}
