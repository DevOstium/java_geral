package com.geral.fox.others;


import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TesteGeraJsonSemBanco {

	public static void main(String[] args) {
		
		ObjetoWrapper wrapper = new ObjetoWrapper();
		
		Objeto o = new Objeto();
		o.setNome("Nome teste 1");
		o.setData(new Integer[]{null, null, 15,20});
		
		Objeto o1 = new Objeto();
		o1.setNome("Nome teste 2");
		o1.setData(new Integer[]{null, 100, 40,1});
		
		wrapper.getLista().add(o);
		wrapper.getLista().add(o1);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(wrapper.getLista()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
