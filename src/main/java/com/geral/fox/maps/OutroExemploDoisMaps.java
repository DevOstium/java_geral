package com.geral.fox.maps;


import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OutroExemploDoisMaps {

	public static void main(String[] args) {
		
		 	//List<String> names =  Arrays.asList("apple,orange,pear".split(","));
		    //List<String> things = Arrays.asList("123,456,789".split(","));
		    List<String> names =  Arrays.asList("apple","orange","pear");
		    List<String> things = Arrays.asList("123","456","789");
		    Map<String,String> map = combineListsIntoOrderedMap (names, things);
		    System.out.println(map);
	}
   static Map<String,String> combineListsIntoOrderedMap (List<String> keys, List<String> values) {
		    if (keys.size() != values.size())
		      throw new IllegalArgumentException ("Cannot combine lists with dissimilar sizes");
		    Map<String,String> map = new LinkedHashMap<String,String>();
		    for (int i=0; i<keys.size(); i++) {
		      map.put(keys.get(i), values.get(i));
		    }
		    return map;
		  }
}
