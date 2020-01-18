package com.geral.arrays;

public class CriarArrayTeste {

	public static void main(String[] args) {

		//Array 1
         String[] produtos = {"Leite", "Arroz"};
         for(int i=0; i< produtos.length; i++) {
        	 System.out.println(produtos[i]);
         }

         
         // Array 2
         String[] pessoas = new String[2];
         pessoas[0] = "Fagner";
         pessoas[1] = "Lira";
         for(int i=0; i < pessoas.length; i++) {
        	 System.out.println(pessoas[i]);
         }
         
	}
}
