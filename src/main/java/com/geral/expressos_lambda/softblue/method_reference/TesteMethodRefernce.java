package com.geral.expressos_lambda.softblue.method_reference;

import java.util.Arrays;
import java.util.List;

public class TesteMethodRefernce {

	public static void main(String[] args) {

		List<String> nomes = Arrays.asList("alfredo", "joana", "ricardo", "marina");

		// nomes.replaceAll(e -> e.toUpperCase());
		// nomes.replaceAll(String::toUpperCase);

		//nomes.replaceAll(e -> TesteMethodRefernce.intercalate(e));
		//nomes.replaceAll(e -> intercalate(e));
		nomes.replaceAll(TesteMethodRefernce::intercalate);

		nomes.forEach(System.out::println);

	}

	private static String intercalate(String s) {
	
		char[]        chars = s.toCharArray();

		boolean       lowerCase = true;
		StringBuilder sb        = new StringBuilder();
		
		for (char c : chars) {

			if (lowerCase) {
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(Character.toUpperCase(c));
			}

			lowerCase = !lowerCase;
		}

		return sb.toString();
	}
}
