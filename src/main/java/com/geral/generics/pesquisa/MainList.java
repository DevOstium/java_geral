package com.geral.generics.pesquisa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainList {

	public static void main(String[] args) {
		

		 Lista l = new Lista();
		 
		 List<Pessoa> el = new ArrayList<>();
		 
		 el.add(new Pessoa("Fagner", new Double(150.00)));
		 
		 l.pro(el);
		
	}
	
	
}
