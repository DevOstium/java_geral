package com.geral.fox.maps;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JoinTwoListOfMaps {

	
	public static void main(String... args){
	    List<Map<String, Object>> list1 = new ArrayList<>();
	    List<Map<String, Object>> list2 = new ArrayList<>();
	    Map<String, Object> map1_1 = new HashMap<>();
	        map1_1.put("id",1);
	        map1_1.put("attr1","a");
	        map1_1.put("attr2","b");
	    list1.add(map1_1);
	    Map<String, Object> map2_1 = new HashMap<>();
	        map2_1.put("id",1);
	        map2_1.put("attr3","x");
	        map2_1.put("attr4","y");
	    list2.add(map2_1);
	    System.out.println(joinTwoListOfMaps(list1, list2));
	}

	@SafeVarargs
	public static List<Map<String, Object>> joinTwoListOfMaps(List<Map<String, Object>>... listsOfMaps){
	    
		List<Map<String, Object>> finalList = Arrays.stream(listsOfMaps).collect(ArrayList::new, List::addAll, List::addAll);
	    
	    return finalList
			   .stream()
			   .collect(ArrayList::new, (list, mapToMerge) ->{
	    	   		Optional<Map<String, Object>> mapWithSameID = list.stream()
			        												  .filter(map -> map.get("id").equals(mapToMerge.get("id")))
			        												  .findFirst();
	        
	        if (mapWithSameID.isPresent()) mapWithSameID.get().putAll(mapToMerge);
	        
	        else list.add(mapToMerge);
	        
	    }, ArrayList::addAll);
	}
	
}
