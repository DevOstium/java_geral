package com.geral.interfaces.exemplo.pagamento.operadoras;

import com.geral.interfaces.exemplo.pagamento.Autorizavel;
import com.geral.interfaces.exemplo.pagamento.Cartao;
import com.geral.interfaces.exemplo.pagamento.Operadora;

public class Cielo implements Operadora {

	@Override
	public boolean autorizar(Autorizavel autorizavel, Cartao cartao) {
		return cartao.getNumeroCartao().startsWith("123") && autorizavel.getValorTotal() < 100;
	}

}
