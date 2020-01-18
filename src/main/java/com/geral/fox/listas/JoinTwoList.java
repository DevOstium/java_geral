package com.geral.fox.listas;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoinTwoList {

	public static void main(String[] args) {
		
		  List<List<Integer>> lol = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6));
		    List<Integer> li = lol.stream().collect(ArrayList::new, List::addAll, List::addAll);
		    System.out.println(lol);
		    System.out.println(li);
	}
	
	
	
	
}
