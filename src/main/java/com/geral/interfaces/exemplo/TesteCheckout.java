package com.geral.interfaces.exemplo;

import com.geral.interfaces.exemplo.caixa.Checkout;
import com.geral.interfaces.exemplo.caixa.Compra;
import com.geral.interfaces.exemplo.impressao.Impressora;
import com.geral.interfaces.exemplo.impressao.impressoras.ImpressoraEpson;
import com.geral.interfaces.exemplo.impressao.impressoras.ImpressoraHP;
import com.geral.interfaces.exemplo.pagamento.Cartao;
import com.geral.interfaces.exemplo.pagamento.Operadora;
import com.geral.interfaces.exemplo.pagamento.operadoras.Cielo;
import com.geral.interfaces.exemplo.pagamento.operadoras.Redecard;

public class TesteCheckout {

	public static void main(String[] args) {
		
		//Operadora  operadora   =  new Cielo();
		//Impressora impressora  =  new ImpressoraHP();
	
		Operadora  operadora   =  new Redecard();
		Impressora impressora  =  new ImpressoraEpson();
		
		Cartao cartao = new Cartao();
		       cartao.setNomeTitular("João M Couves");
		       cartao.setNumeroCartao("456");
		
		Compra compra = new Compra();
		       compra.setNomeCliente("João Mendonça Couves");
		       compra.setProduto("Sabonete");
		       compra.setValorTotal(3);
		
		Checkout checkout = new Checkout(operadora, impressora);
		         checkout.fecharCompra(compra, cartao);
	}
	
}
