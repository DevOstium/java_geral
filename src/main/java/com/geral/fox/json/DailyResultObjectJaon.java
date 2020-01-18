package teste.json;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.foxconn.tdk.dao.fpy.FPYDAO;
import br.com.foxconn.tdk.model.fpy.DataPoint;
import br.com.foxconn.tdk.model.fpy.FpyChart;
import br.com.foxconn.tdk.model.fpy.FpyChartFilter;
import br.com.foxconn.tdk.model.fpy.FpyLine;
import br.com.foxconn.tdk.model.fpy.FpyMaxProducts;
import br.com.foxconn.tdk.model.fpy.FpyTimeInterval;

public class DailyResultObjectJaon {

	public static void main(String[] args) throws ParseException {
			
		FpyChartFilter filter   = new FpyChartFilter();

		//filter.setWork_id_begin("20170201");
		//filter.setWork_id_end(  "20170430");
		//filter.setModelNameList(Arrays.asList("9WC1G"));
		
		filter.setTypeTimeChart("DAILY");
		filter.setGroup_name("OVER ALL");
		
		System.out.println(filter.getTypeTimeChart());
		
		FPYDAO dao         = new FPYDAO();
		FpyChart fpyChart  = new FpyChart();
		FpyChartFilter f   = new FpyChartFilter();
		
		f.setTypeTimeChart(filter.getTypeTimeChart());
		f.setGroup_name(filter.getGroup_name());
		
		FpyTimeInterval timeInterval = dao.getTimeIntervalChart(f);
		
//		Calendar c = new GregorianCalendar();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHMM");
//
//		c.set(2017, Calendar.MARCH, 23, 0, 0);
//		Date from = c.getTime();
//		f.setFrom(from);
//		String p_from = sdf.format(from);
//		
//		c.set(2017, Calendar.MARCH, 24, 23, 0);
//		Date to = c.getTime();
//		f.setTo(to);
//		String p_to   = sdf.format(to);

		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    
	    Calendar now = new GregorianCalendar();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        String k = sdf2.format(now.getTime());
        Date from = sdf2.parse(k);
        f.setFrom(from);
        String p_from = sdf3.format(now.getTime());
       
        
        now.set(Calendar.HOUR_OF_DAY, 23);
        String q = sdf2.format(now.getTime());
        Date to = sdf2.parse(q);
        f.setTo(to);
        String p_to = sdf3.format(now.getTime());
        
        f.setWork_id_begin(p_from);
		f.setWork_id_end(p_to);
	
		//System.out.println("p_from " + p_from);
		//System.out.println("p_to " + p_to);
		
		List<String>         fillMaxProducts = new ArrayList<String>();
		List<FpyMaxProducts> maxProducts     = dao.getMaxProductChart(f);
		
		for (FpyMaxProducts fpyMaxProducts : maxProducts) {
			System.out.println(fpyMaxProducts.getModel_name());
				fillMaxProducts.add(fpyMaxProducts.getModel_name());
		}
		
		f.setModelNameList(fillMaxProducts);

		//System.out.println("filter.getModelNameList() :" + filter.getModelNameList());
		//System.out.println("filter.getWork_id_begin() :" + filter.getWork_id_begin());
	
		if(filter.getWork_id_begin() != null && filter.getModelNameList() == null){
			String dateFrom = filter.getWork_id_begin();
			String dateTo = filter.getWork_id_end();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");
			Date dateF =  sdf.parse(dateFrom);
			Date dateT =  sdf.parse(dateTo);
			
			Calendar c = new GregorianCalendar();
			
			c.setTime(dateF);
			Date fromFilter = c.getTime();
			f.setFrom(fromFilter);
			
			c.setTime(dateT);
			Date dateToFilter = c.getTime();
			f.setTo(dateToFilter);
			
			f.setWork_id_begin(filter.getWork_id_begin());
			f.setWork_id_end(filter.getWork_id_end());
			
			f.setModelNameList(fillMaxProducts);
		} 
		
		
		if(filter.getModelNameList() != null ){
			String dateFrom = filter.getWork_id_begin();
			String dateTo = filter.getWork_id_end();
		   
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");
			Date dateF =  sdf.parse(dateFrom);
			Date dateT =  sdf.parse(dateTo);
			
		    Calendar c = new GregorianCalendar();
		    
		    c.setTime(dateF);
		    Date fromFilter = c.getTime();
		    f.setFrom(fromFilter);
		    //System.out.println("fromFilter " + fromFilter);
		    
		    c.setTime(dateT);
			Date dateToFilter = c.getTime();
			f.setTo(dateToFilter);
			//System.out.println("dateToFilter " + dateToFilter);
		    
			f.setWork_id_begin(filter.getWork_id_begin());
			f.setWork_id_end(filter.getWork_id_end());
		
			List<String> list = new ArrayList<String>();
			for (String s : filter.getModelNameList()) {
				if(!s.equals("0")){
					list.add(s);
				} else {
					for (FpyMaxProducts fpyMaxProducts : maxProducts) {
						list = Arrays.asList(fpyMaxProducts.getModel_name());
					}
				}
			}
			f.setModelNameList(list);
		} else{
			f.setModelNameList(fillMaxProducts);
		}
		
		
		List<FpyLine> linesData =  new ArrayList<FpyLine>();
		for (String s : f.getModelNameList()) {
			System.out.println("valor de s: " + s);
			f.setSelectedModel(s);
			
			List<DataPoint> data = dao.getDailyLineSeriesByFilter(f);
			System.out.println(data.size());
			
			if(data.size() != 0){
				FpyLine series = new FpyLine();
					Set<String>         set     = new HashSet<String>();
					Map<String, String> mapDesc = new LinkedHashMap<String, String>();
					
					for (DataPoint dataPoint : data) {
						System.out.println("dataPoint.getModel_name()" + dataPoint.getModel_name() +" - " +"dataPoint.getValue() " + dataPoint.getValue());
						set.add(dataPoint.getModel_description());
						mapDesc.put(dataPoint.getModel_name(), dataPoint.getModel_description());
					}
					
					fillNameSeriesMap(mapDesc);
					
					List<Double> filledData = fillData(f, data);
					for (Double d : filledData) {
					}

					series.setData(filledData);
					series.setName(fillNameSeriesMap(mapDesc));

					linesData.add(series);
			}
					
		}// end for linesData
		
		fpyChart.setSeries(linesData);
		fpyChart.setCategories(categories(f));
		fpyChart.setTitle(f.toString());
		fpyChart.setTitleWeekly(f.toTitleWeekly());
		fpyChart.setTitleMonthly(f.toTitleMonthly());
		fpyChart.setTitleTop(f.toTitle());
		fpyChart.setTypeTimeChart(f.getTypeTimeChart());
		fpyChart.setTimeInterval(timeInterval.getTime_interval());
		
		
		fpyChart.setInputFilterFrom(f.toInputFilterFrom());
		fpyChart.setInputFilterTo(f.toInputFilterTo());
		
		
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = "";
		try {
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fpyChart);
			//jsonInString = mapper.writeValueAsString(fpyChart);
			System.out.println(jsonInString);
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println("fpyChart.getTitle() " + fpyChart.getTitle());
		
	   //return fpyChart;
		
	}
	
	
	
	  private static List<String> fillNameSeriesMap(Map<String, String> mapDesc) {
	    	 List<String> t = new ArrayList<String>();
	    	 for(Map.Entry<String, String> h : mapDesc.entrySet()){
	    		t = Arrays.asList(h.getKey(), h.getValue());
	    	 }
	    	return t;
	 }
	  
	 private static List<String> categories(FpyChartFilter f) {
			
		 Calendar calendar = new GregorianCalendar();
			
		 //System.out.println(" f.getFrom() "  + f.getFrom());
		 //System.out.println(" f.getTo() "  + f.getTo());
			calendar.setTime(f.getFrom());
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH");
			
			List<String> xAis = new ArrayList<>();
			
			boolean getOut = false;
			
			while(!getOut){
				String hour = sdf.format(calendar.getTime());
				xAis.add(hour);
				
				calendar.add(Calendar.HOUR_OF_DAY, 1);
				
				if (calendar.getTimeInMillis() > new Date().getTime()) {
					String hour2 = sdf.format(calendar.getTime());
					xAis.add(hour2);
					getOut = true;
					continue;
				}
				
				//System.out.println("calendar.getTime() " + calendar.getTime());
				
				if(calendar.getTimeInMillis() == f.getTo().getTime() ){
					String hour2 = sdf.format(calendar.getTime());
					xAis.add(hour2);
					getOut = true;
				} else{
					getOut = false;
				}
			}
			return xAis;
		} // end categories


	private static List<Double> fillData(FpyChartFilter f, List<DataPoint> data) {
	
		//Transform data to Map
		Map<String, Double> dataMap = new HashMap<>(); 
		
		for (DataPoint dp : data) {
			dataMap.put(dp.getMesDataHora(), dp.getValue());
		}
	
		//Java 8 only, forEach and Lambda
		//dataMap.forEach((k,v)->System.out.println("Key MAP é : " + k + " Value MAP é: " + v));
		
		System.out.println(" f.getFrom() "  + f.getFrom());
		System.out.println(" f.getTo() "  + f.getTo());
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(f.getFrom());
		//System.out.println(" calendar.getTime() "  + calendar.getTime());
		
		List<Double> ret = new ArrayList<>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
		boolean getOut = false;
		while (!getOut) {
			

			String hour = sdf.format(calendar.getTime());
			//System.out.println("hour " + hour);
			Double result = dataMap.get(hour);
			if (result == null) {
				if (calendar.getTimeInMillis() > new Date().getTime()) {
					ret.add(null);
				} else {
					ret.add(0.0);
				}
			} else if(result < 0 ){
				ret.add(0.0);
			}
			else {
				ret.add(result);
			}
			
			if (calendar.getTimeInMillis() > new Date().getTime()) {
				getOut = true;
				continue;
			}
			
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			System.out.println(calendar.getTime());
			if (calendar.getTimeInMillis() == f.getTo().getTime()) {
			
				String hour2 = sdf.format(calendar.getTime());
				//System.out.println("hour " + hour2);
				Double result2 = dataMap.get(hour2);
				if (result2 == null) {
					ret.add(0.0);
				} else {
					ret.add(result2);
				}
				
				getOut = true;

			} else {
				getOut = false;
			}
			
		}
		return ret;
	}
	
	
	
}// DailyResultObjectJaon
