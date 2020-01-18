package com.geral.fox.files.split;


import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class UsandoSplit {

	public static void main(String[] args) {
		
		//quantidades de item lidos, ap�s a separa��o por virgula(,)
		System.out.println(Arrays.asList(("banana,ma��,laranja").split(",")).size());  
		
		// Itens contidos na lista, apos a separa��o por virgula(,)
		List<String> list = Arrays.asList(("banana,ma��,laranja").split(","));
		for(String a : list){
			System.out.println(a);
		}
		
		
		String a = "Exemplo, de. separar- string+ por* carater";
	    //Como quer todos os caracteres pode usar esta express�o regular:
	    String[] parts = a.split("[\\W][ ]");;

	    for(String i:parts){
	        System.out.println("===" +i);
	    }
		
	    
	    String line = "12#3@4_5/6.7*8";
	    String caracteres = " #@_\\/.*";
	    String words[] = line.split("[" + Pattern.quote(caracteres) + "]");
	    for (String string : words) {
	        System.out.print(string + " ");
	    }
		
	    System.out.println(" ----------   ");
	    String text = "405R";
	    Arrays.asList(text.split(""));
	    System.out.println(Arrays.asList(text.split("")));
	    
	    List<String> res = Arrays.asList(text.split(""));
	    for(String c: res){
	    	System.out.println(c);
	    }
	    
	    
	}
}





















