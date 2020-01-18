package com.geral.stream_API.softblue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Exemplo1 {

	
	private static List<String> itens = 
			Arrays.asList("computador", "papel", "caneta", "quadro", "borracha", "cortina", "Dado", "Bola", "gaveta", "Zebra", "Amora", "Lado");

	// DICA : Quando usar o distinct lembrar de implementar o hashCode() E equals da classe
	
	public static void main(String[] args) {
		//executaFilter();
		//executar1();
		//executar2();
		executar3();
	}

	private static void executaFilter() {
		
		// Um operacao de Reducao = resultado final, por exemplo gerar uma nova lista
		// Podemos chamara de PIPELINE, quando encadeada
		itens.stream()
		     //.sorted(   Comparator.naturalOrder())      // Em ordem crescente
			 .sorted  (   Comparator.reverseOrder()  )         // Ordem Decrescente
		     .filter  (   s -> s.startsWith("c")  )
		     .map     (   d -> d.toUpperCase()    )         // Transformar o elemento em outro
		     .collect (   Collectors.toList()     )
		     .forEach (   System.out::println     );
		
	}
	
	private static void executar1() {
		// 1. Ordenar
		// 2. Manter só os 3 primeiros elementos  :  .limit(3)
		// 3. Mostrar o resultado na tela
		
		itens.stream()
			 //.sorted()
			 .sorted(Comparator.naturalOrder())    // Ordem Crescente
			 //.sorted(Comparator.reverseOrder())  // Ordem Decrescente
		     //.sorted(Comparator.comparing(User::getNome))
			 //.limit(3)
			 .forEach(System.out::println);
	}
	
	private static void executar2() {
		// 1. Filtrar só elementos com a letra 'c'
		// 2. Montar uma nova lista com estes elementos

		List<String> itensFiltrados = itens.stream()
			                               .filter(e -> e.contains("c"))
			                               .collect(Collectors.toList());
		
		System.out.println(itensFiltrados);
	}
	
	private static void executar3() {
		// 1. Somar a quantidade de caracteres de todos os elementos
		// 2. Retornar a soma

		
		Long tamanhoLista = itens.stream().collect( Collectors.counting() );
		System.out.println("tamanhoLista : " + tamanhoLista);
		
		// SOMAR
		int count = itens.stream().collect( Collectors.summingInt( e -> e.length() ) );
		
		//int total = itens.stream().collect( Collectors.summingInt( e -> e.getQuantidade ) );
		
		System.out.println("Somando todas as Strings : " + count);
	}
}








