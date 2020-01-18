package com.geral.expressos_lambda.softblue.collections;


public class TesteThread {

	
	public static void main(String[] args) {
		
		new Thread( () -> System.out.println("Come�ou") ).start();
		
	}
	
}
