package teste.json;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.foxconn.tdk.dao.fpy.FPYDAO;
import br.com.foxconn.tdk.model.fpy.DataPointMonthly;
import br.com.foxconn.tdk.model.fpy.FpyChart;
import br.com.foxconn.tdk.model.fpy.FpyChartFilter;
import br.com.foxconn.tdk.model.fpy.FpyLine;
import br.com.foxconn.tdk.model.fpy.FpyMaxProducts;
import br.com.foxconn.tdk.model.fpy.FpyTimeInterval;

public class MonthlyReultObjectJson {

	public static void main(String[] args) throws ParseException {
		FpyChartFilter filter   = new FpyChartFilter();

		//filter.setWork_id_begin("20170201");
		//filter.setWork_id_end(  "20170430");
		//filter.setModelNameList(Arrays.asList("9WC1G"));
		filter.setTypeTimeChart("MONTHLY");
		

		FPYDAO dao         = new FPYDAO();
		FpyChart fpyChart  = new FpyChart();
		FpyChartFilter f   = new FpyChartFilter();
		
		f.setGroup_name("ICT");
		f.setTypeTimeChart(filter.getTypeTimeChart());
		FpyTimeInterval timeInterval = dao.getTimeIntervalChart(f);
		
		
	    LocalDate today = LocalDate.now();
        LocalDate firstDayMonth = today.withDayOfMonth(1);
        LocalDate lastDayMonth = today.withDayOfMonth(today.getMonth().maxLength());

        System.out.println(firstDayMonth);
        System.out.println(lastDayMonth);
        
        DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("yyyyMMdd");
        SimpleDateFormat sdf7 = new SimpleDateFormat("yyyyMMdd");

        String parsefrom = firstDayMonth.format(formatador);
        String parseto   = lastDayMonth.format(formatador);
       
		Date from =  sdf7.parse(parsefrom);
		f.setFrom(from);
		System.out.println(" dateFrom " + from);

		Date to =  sdf7.parse(parseto);
		f.setTo(to);
		System.out.println(" dateTo " + to);
		
		String p_work_id_begin = firstDayMonth.format(formatador)+"00";
		String p_work_id_end   = lastDayMonth.format(formatador)+"23";
		
		f.setWork_id_begin(p_work_id_begin);
		f.setWork_id_end(p_work_id_end);
		
		System.out.println(" p_work_id_begin " + p_work_id_begin);
		System.out.println(" p_work_id_end " + p_work_id_end);
			 
		List<String> fillMaxProducts = new ArrayList<String>();
		List<FpyMaxProducts> maxProducts = dao.getMaxProductChart(f);
	
		for (FpyMaxProducts fpyMaxProducts : maxProducts) {
			//System.out.println(fpyMaxProducts.getModel_name());
			fillMaxProducts.add(fpyMaxProducts.getModel_name());
		}
		
		f.setModelNameList(fillMaxProducts);

		//System.out.println("filter.getModelNameList() :" + filter.getModelNameList());
		//System.out.println("filter.getWork_id_begin() :" + filter.getWork_id_begin());
	
		if(filter.getWork_id_begin() != null && filter.getModelNameList() == null){
			String dateFrom = filter.getWork_id_begin();
			String dateTo = filter.getWork_id_end();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date dateF =  sdf.parse(dateFrom);
			Date dateT =  sdf.parse(dateTo);
			
			Calendar c = new GregorianCalendar();
			
			c.setTime(dateF);
			Date fromFilter = c.getTime();
			f.setFrom(fromFilter);
			
			c.setTime(dateT);
			Date dateToFilter = c.getTime();
			f.setTo(dateToFilter);
			
			f.setWork_id_begin(dateFrom+"00");
			f.setWork_id_end(dateTo+"23");
			
			f.setModelNameList(fillMaxProducts);
		} 
		
		
		if(filter.getModelNameList() != null ){
			String dateFrom = filter.getWork_id_begin();
			String dateTo = filter.getWork_id_end();
		   
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date dateF =  sdf.parse(dateFrom);
			Date dateT =  sdf.parse(dateTo);
			
		    Calendar c = new GregorianCalendar();
		    
		    c.setTime(dateF);
		    Date fromFilter = c.getTime();
		    f.setFrom(fromFilter);
		    
		    c.setTime(dateT);
			Date dateToFilter = c.getTime();
			f.setTo(dateToFilter);
		    
			f.setWork_id_begin(dateFrom+"00");
			f.setWork_id_end(dateTo+"23");
		
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
	    		f.setSelectedModel(s);
	    		
	    		List<DataPointMonthly> data  = dao.getMonthlyLineSeriesByFilter(f);
	    		
	    		if(data.size() != 0){
	    			FpyLine series = new FpyLine();
			    		Set<String> set = new HashSet<String>();
			    		Map<String, String> mapDesc = new LinkedHashMap<String, String>();
			    		
			    		for (DataPointMonthly dataPoint : data) {
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
	    	}// end linesData
	    	
		  
		
		
		
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

	}// end main
	
	

		private static List<String> fillNameSeriesMap(Map<String, String> mapDesc) {
		    	 List<String> t = new ArrayList<String>();
		    	 for(Map.Entry<String, String> h : mapDesc.entrySet()){
		    		t = Arrays.asList(h.getKey(), h.getValue());
		    	 }
		    	return t;
		 }
		  
		
		
		
		 private static List<String> categories(FpyChartFilter f) throws ParseException {
				
			 Calendar calendar = new GregorianCalendar();
			 	
			 System.out.println(" f.getFrom() "  + f.getFrom());
			 System.out.println(" f.getTo() "  + f.getTo());
			 
			 calendar.setTime(f.getFrom());
			
			 SimpleDateFormat sdfWeekly = new SimpleDateFormat("d", Locale.ENGLISH);
			 
			 List<String> xAis = new ArrayList<>();
			 	
			 LocalDate todayStop = LocalDate.now();
			 LocalDate lastDayMonthStop = todayStop.withDayOfMonth(todayStop.getMonth().maxLength());
	         DateTimeFormatter fStop =  DateTimeFormatter.ofPattern("yyyyMMdd");
	         SimpleDateFormat sdfStop = new SimpleDateFormat("yyyyMMdd");
	         String parseStop   = lastDayMonthStop.format(fStop);
	         Date stop =  sdfStop.parse(parseStop);
			 
			 boolean getOut = false;
				 while(!getOut){
						String day = sdfWeekly.format(calendar.getTime());
						xAis.add(day);
						calendar.add(Calendar.DAY_OF_MONTH, 1);
						System.out.println(calendar.getTime());

						if (calendar.getTimeInMillis() > stop.getTime()) {
							String hour2 = sdfWeekly.format(calendar.getTime());
							xAis.add(hour2);
							getOut = true;
							continue;
						}
						
						if(calendar.getTimeInMillis() == f.getTo().getTime() ){
							String day2 = sdfWeekly.format(calendar.getTime());
							xAis.add(day2);
							getOut = true;
						} else{
							getOut = false;
						}
					} // end while DAILY
			 
				return xAis;
			} // end categories


		  private static List<Double> fillData(FpyChartFilter f, List<DataPointMonthly> data) throws ParseException {
				//Transform data to Map
				Map<String, Double> dataMap = new HashMap<>(); 
				
				for (DataPointMonthly dp : data) {
					dataMap.put(dp.getKey_date_monthly(), dp.getValue());
				}
			
				//Java 8 only, forEach and Lambda
				//dataMap.forEach((k,v)->System.out.println("Key MAP é : " + k + " Value MAP é: " + v));
				
				//System.out.println(" f.getFrom() "  + f.getFrom());
				//System.out.println(" f.getTo() "  + f.getTo());
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(f.getFrom());
				//System.out.println(" calendar.getTime() "  + calendar.getTime());
				
				List<Double> ret = new ArrayList<>();
				
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				
				 LocalDate todayStop = LocalDate.now();
				 LocalDate lastDayMonthStop = todayStop.withDayOfMonth(todayStop.getMonth().maxLength());
		         DateTimeFormatter fStop =  DateTimeFormatter.ofPattern("yyyyMMdd");
		         SimpleDateFormat sdfStop = new SimpleDateFormat("yyyyMMdd");
		         String parseStop   = lastDayMonthStop.format(fStop);
		         Date stop =  sdfStop.parse(parseStop);
				
				
				boolean getOut = false;
				while (!getOut) {
					String dayOfMonth = sdf.format(calendar.getTime());
					//System.out.println("hour " + hour);
					Double result = dataMap.get(dayOfMonth);
					if (result == null) {
						if (calendar.getTimeInMillis() > stop.getTime()) {
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
					
					if (calendar.getTimeInMillis() > stop.getTime()) {
						getOut = true;
						continue;
					}
					
					calendar.add(Calendar.DAY_OF_MONTH, 1);
					//System.out.println(calendar.getTime());
					if (calendar.getTimeInMillis() == f.getTo().getTime()) {
					
						String dayOfMonth2 = sdf.format(calendar.getTime());
						//System.out.println("hour " + hour2);
						Double result2 = dataMap.get(dayOfMonth2);
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
			} // end fillData
		

}//end class