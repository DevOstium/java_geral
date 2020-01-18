package com.geral.polimorfismo_instanceof_casting;

public class TestePolimorfismo {

	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setSaldo(4000);

		// ContaCorrente contaCorrente = new ContaCorrente();
		Conta contaCorrente = new ContaCorrente();
		contaCorrente.setSaldo(2300);

		// ContaPoupanca contaPoupanca = new ContaPoupanca();
		Conta contaPoupanca = new ContaPoupanca();
		contaPoupanca.setSaldo(9800);

		TestePolimorfismo.imprimirSaldo(conta);
		TestePolimorfismo.imprimirSaldo(contaCorrente);
		TestePolimorfismo.imprimirSaldo(contaPoupanca);
	}

	public static void imprimirSaldo(Conta conta) {

		//Dica: Veja que o valor da classe saldo nao esta sendo impresso
		
		if (conta instanceof ContaCorrente) {
			System.err.println(" instanceof de ContaCorrente");
			ContaCorrente cc = (ContaCorrente) conta;
			System.out.println("Usando o Casting de objetos para imprimir o limite - ContaCorrente : " + cc.getLimite());
		}

		if (conta instanceof ContaPoupanca) {
			System.err.println(" instanceof de ContaPoupanca");
			ContaPoupanca cc = (ContaPoupanca) conta;
			System.out.println("Usando o Casting de objetos para imprimir o limite - ContaPoupanca : " + cc.getRendimentos());
		}
		
		

	}

}
