package com.geral.stream_API.softblue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Exemplo2 {

private static List<Produto> produtos;
	
	static {
		produtos = new ArrayList<>();
		produtos.add(new Produto( 1,  "Batata"  ,   3.5));
		produtos.add(new Produto( 2,  "Tomate"  ,   4.3));
		produtos.add(new Produto( 3,  "Cebola"  ,   2.9));
		produtos.add(new Produto( 4,  "Cenoura" ,   1.8));
		produtos.add(new Produto( 5,  "Vagem"   ,   3.2));
	}

	public static void main(String[] args) {
		executar1();
		executar2();
		executar3();
	}

	private static void classePorReferencia() {
		List<String> lista = Arrays.asList("Fagner", "Lira");
		
		                                   // mesmo que = map( e -> new Pessoa(e))
		                                   // e = Fagner, Lira
		List<Pessoa> pessoas = lista.stream().map(Pessoa::new).collect(Collectors.toList());
	}
	
	private static void executar1() {
		// 1. Ordenar os produtos por preço
		// 2. Converter os preços em dólar
		// 3. Mostrar os dados do produto na tela

		double cotacao = 4;  // Usar uma variavel de fora de uma expressao lambda = closure
		
		produtos.stream()
			    .sorted  (   (e1, e2) ->  Double.valueOf(e1.getPreco()).compareTo(e2.getPreco())               )
			    .map     (   p        ->  new Produto(    p.getId(),   p.getNome(),  p.getPreco() / cotacao )  )
			    .forEach (   p        ->  System.out.println(  p.getNome() + " -> " + p.getPreco() )           );
	}
	
	private static void executar2() {
		// 1. Criar um Map onde a chave é o ID e o valor é o nome do produto

		Map<Integer, String> map = produtos.stream().collect(  Collectors.toMap(  p ->  p.getId(), p -> p.getNome() ) );
		
		System.out.println(map);
	}
	
	private static void executar3() {
		// 1. Criar uma representação de texto dos produtos
		
		// %d    =  p.getId(
		// %s    =  p.getNome()
		// %.2f  =  p.getPreco()
		
		String str = produtos.stream()
			                  .map( p ->  String.format( "%d:%s:%.2f", p.getId(), p.getNome(), p.getPreco() ) )
			                  //.collect(Collectors.joining(";"));
							  .collect(Collectors.joining("; \n"));  // \n quebra de linha
		
		System.out.println(str);
	}
}
