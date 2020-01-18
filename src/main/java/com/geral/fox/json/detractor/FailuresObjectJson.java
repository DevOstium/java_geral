package teste.json.detractor;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.foxconn.tdk.dao.detractor.DetractorDAO;
import br.com.foxconn.tdk.model.detractor.DateLineFailure;
import br.com.foxconn.tdk.model.detractor.FailuresChart;
import br.com.foxconn.tdk.model.detractor.FilterFailuresChart;
import br.com.foxconn.tdk.model.detractor.SeriesFailue;

public class FailuresObjectJson {

	public static void main(String[] args) throws ParseException {
		
		FilterFailuresChart filterInput = new FilterFailuresChart();
		
		DetractorDAO         dao                = new DetractorDAO();
		FailuresChart        failuresChart      = new FailuresChart();
		FilterFailuresChart  filterLocal        = new FilterFailuresChart(); 
		
		filterInput.setTitleFailure("Top Failures");
		getIntervalDateDaily(filterInput, filterLocal);
		
		filterLocal.setTitleFailure(filterInput.getTitleFailure());
	   
//		List<DateLineFullFailure> li =  dao.getFailuresFullNovoByFilter(filterLocal);
//	   
//		List<DateLineFullFailure> result = new ArrayList<>();
//		
//		// Se a lista tiver 20 ou menos, o resultado final é ele próprio
//		if(li.size() <= 20) {
//			
//			result.addAll(li);
//			
//		}else {
//			
//			// Adiciona os 20 primeiros
//			result.addAll(li.subList(0, 20));
//			
//			// Se existirem apenas 21, adiciona o 21º
//			if(li.size() == 21) {
//				
//				result.add(li.get(21));
//				
//			}else {
//				
//				DateLineFullFailure outros = null;
//				
//				// Para cada um a partir do 21º
//				for(DateLineFullFailure aux : li.subList(21, li.size() - 1)) {
//					
//					// Se nenhum a foi atribuido, atribui o 21º
//					if(outros == null) {
//						
//						outros = aux;
//						
//						outros.setFailure_code("OUTROS");
//						outros.setFailure_description("OUTROS");
//						
//					}else {
//						
//						// Soma a quantidade dos demais, a quantidade do 21º 
//						outros.setQtde(outros.getQtde() + aux.getQtde());
//					}
//				}
//				
//				// Adiciona o "outros"
//				result.add(outros);
//			}
//		}
		
		
		
		
		List<DateLineFailure> li =  dao.getFailures(filterLocal);
		List<DateLineFailure> result = new ArrayList<DateLineFailure>();
		if(li.size() <= 20) {
			result.addAll(li);
		}else {
			result.addAll(li.subList(0, 20));
			if(li.size() == 21) {
				result.add(li.get(21));
			}else {
				DateLineFailure outros = null;
				for(DateLineFailure aux : li.subList(21, li.size() - 1)) {
					if(outros == null) {
						outros = aux;
						outros.setFailure_code("OUTROS");
						outros.setFailure_description("OUTROS");
					}else {
						outros.setPoint_value(outros.getPoint_value() + aux.getPoint_value());
					}
				}
				result.add(outros);
			}
		}
		
		List<SeriesFailue> serieList = new ArrayList<SeriesFailue>();
		SeriesFailue  serie = new SeriesFailue();
		serie.setData(result);
		serieList.add(serie);
		
		failuresChart.setSeries(serieList);
		failuresChart.setTitleFailure(filterLocal.getTitleFailure());
		failuresChart.setInputFilterDateFrom(filterLocal.toIputFilterDateFrom());
		failuresChart.setInputFilterDateTo(filterLocal.toInputFilterDateTo());
    	
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = "";
		try {
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(failuresChart);
			System.out.println(jsonInString);
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
	} // end main



	private static void getIntervalDateDaily(FilterFailuresChart filterInput, FilterFailuresChart filterLocal) {
		LocalDateTime today = LocalDateTime.now();
		System.out.println(today.with(LocalTime.MIN));
		System.out.println(today.with(LocalTime.MAX));
		
		LocalDateTime ldFrom = today.with(LocalTime.MIN);
		LocalDateTime ldTo   = today.with(LocalTime.MAX);
		
		Date dateFrom =  Date.from(ldFrom.atZone(ZoneId.systemDefault()).toInstant());
		Date dateTo   =  Date.from(ldTo.atZone(ZoneId.systemDefault()).toInstant());
		
		filterLocal.setFrom(dateFrom);
		filterLocal.setTo(dateTo);
		
		System.out.println(" dateFrom :" + filterLocal.getFrom());
		System.out.println(" dateFrom :" + filterLocal.getTo());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
		String work_id_begin = sdf.format(dateFrom); 
		String work_id_end   = sdf.format(dateTo); 
		
		filterLocal.setWork_id_begin(work_id_begin);
		filterLocal.setWork_id_end(work_id_end);
		
		System.out.println(" work_id_begin :" + filterLocal.getWork_id_begin());
		System.out.println(" work_id_end :" + filterLocal.getWork_id_end());
		System.out.println("  ********************  ");
	}
	    
	
	
	
} // end FailuresObjectJson
