package teste.json;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.foxconn.tdk.dao.fpy.FPYDAO;
import br.com.foxconn.tdk.model.fpy.DataPoint;
import br.com.foxconn.tdk.model.fpy.DataPointMonthly;
import br.com.foxconn.tdk.model.fpy.DataPointWeekOfYear;
import br.com.foxconn.tdk.model.fpy.DataPointWeekly;
import br.com.foxconn.tdk.model.fpy.DataPointYearly;
import br.com.foxconn.tdk.model.fpy.FpyChart;
import br.com.foxconn.tdk.model.fpy.FpyChartFilter;
import br.com.foxconn.tdk.model.fpy.FpyLine;
import br.com.foxconn.tdk.model.fpy.FpyMaxProducts;
import br.com.foxconn.tdk.model.fpy.FpyTimeInterval;

public class GroupAllReultObjectJson {

	public static void main(String[] args) throws ParseException {

	FpyChartFilter filterInput = new FpyChartFilter();
	
	filterInput.setTypeTimeChart("DAILY");
	filterInput.setGroup_name("ICT");
	
	FPYDAO dao         = new FPYDAO();
	FpyChart fpyChart  = new FpyChart();
	FpyChartFilter filterLocal   = new FpyChartFilter();
	
	filterLocal.setGroup_name(filterInput.getGroup_name());
	filterLocal.setTypeTimeChart(filterInput.getTypeTimeChart());
	FpyTimeInterval timeInterval = dao.getTimeIntervalChart(filterLocal);
	
	List<String> fillMaxProducts = new ArrayList<String>();
	List<FpyMaxProducts> maxProducts = dao.getMaxProductChart(filterLocal);

	for (FpyMaxProducts fpyMaxProducts : maxProducts) {
		fillMaxProducts.add(fpyMaxProducts.getModel_name());
	}
	
	filterLocal.setListTypeTimeChart(Arrays.asList("YEARLY", "MONTHLY", "WEEK_OF_YEAR", "WEEK_OF_MONTH", "DAILY"));
	filterLocal.setModelNameList(fillMaxProducts);
	
	
				List<FpyLine> linesData =  new ArrayList<FpyLine>();
				for (String s : filterLocal.getModelNameList()) {
					
					FpyLine series = new FpyLine();
					filterLocal.setSelectedModel(s);
					
					List<String>               dataTypeTime     = filterLocal.getListTypeTimeChart();
					Map<String, String>        mapDescricao     = new LinkedHashMap<String, String>();
					
					List<DataPointYearly>      dataYearly        = new ArrayList<DataPointYearly>();
					List<DataPointMonthly>     dataMonthly       = dao.getMonthlyLineSeriesByFilter(filterLocal);	
					List<DataPointWeekOfYear>  dataWeekOfYear    = dao.getWeekOfYearLineSeriesByFilter(filterLocal);
					List<DataPointWeekly>      dataWeekOfMonth   = dao.getWeeklyLineSeriesByFilter(filterLocal);
					List<DataPoint>            dataDaily         = dao.getDailyLineSeriesByFilter(filterLocal);

					
							//"YEARLY", "MONTHLY", "WEEK_OF_YEAR", "WEEK_OF_MONTH", "DAILY"
							for (String t : filterLocal.getListTypeTimeChart()){
									if(t.equals("YEARLY")){
										System.out.println("Estou no YEARLY");
										getIntervalDateYearly(filterLocal);
										dataYearly  = dao.getYearlyLineSeriesByFilter(filterLocal);
				
										for (DataPointYearly dataPoint : dataYearly) {
											 mapDescricao.put(dataPoint.getModel_name(), dataPoint.getModel_description());
										}
									}// end "YEARLY"
									if(t.equals("MONTHLY")){
										System.out.println("Estou no MONTHLY");
										getIntervalDateMonthly(filterLocal);
										dataMonthly       = dao.getMonthlyLineSeriesByFilter(filterLocal);	
										
										for (DataPointYearly dataPoint : dataYearly) {
											mapDescricao.put(dataPoint.getModel_name(), dataPoint.getModel_description());
										}
									}// end "MONTHLY"
									if(t.equals("WEEK_OF_YEAR")){
										System.out.println("Estou no WEEK_OF_YEAR");
										getIntervalDateWeekOfYear(filterLocal);
										dataWeekOfYear    = dao.getWeekOfYearLineSeriesByFilter(filterLocal);
										
										for (DataPointYearly dataPoint : dataYearly) {
											mapDescricao.put(dataPoint.getModel_name(), dataPoint.getModel_description());
										}
									}// end "MONTHLY"
									if(t.equals("WEEK_OF_MONTH")){
										System.out.println("Estou no WEEK_OF_MONTH");
										getIntervalDateWeekOfMonth(filterLocal);
										 dataWeekOfMonth   = dao.getWeeklyLineSeriesByFilter(filterLocal);
										
										for (DataPointYearly dataPoint : dataYearly) {
											mapDescricao.put(dataPoint.getModel_name(), dataPoint.getModel_description());
										}
									}// end "MONTHLY"
									if(t.equals("WEEK_OF_MONTH")){
										System.out.println("Estou no WEEK_OF_MONTH");
										getIntervalDateDaily(filterLocal);
										dataDaily         = dao.getDailyLineSeriesByFilter(filterLocal);
										
										for (DataPointYearly dataPoint : dataYearly) {
											mapDescricao.put(dataPoint.getModel_name(), dataPoint.getModel_description());
										}
									}// end "MONTHLY"
							} // end for
						
				    		
				    		series.setName(fillNameSeriesMap(mapDescricao));
				    		series.setData(fillData(filterLocal
				    				               ,filterInput
				    				               ,dataTypeTime
				    				               ,dataYearly     
				    				               ,dataMonthly    
				    				               ,dataWeekOfYear 
				    				               ,dataWeekOfMonth
				    				               ,dataDaily      
				    				               )
				    				       );
				    		
				    		linesData.add(series);
				}// end for linesData
		

				ObjectMapper mapper = new ObjectMapper();
				
				fpyChart.setSeries(linesData);
				fpyChart.setCategories(categories(filterLocal, filterInput));
				fpyChart.setTitle(filterLocal.toString());
				fpyChart.setTitleWeekly(filterLocal.toTitleWeekly());
				fpyChart.setTitleMonthly(filterLocal.toTitleMonthly());
				fpyChart.setTitleYearly(filterLocal.toTitleYearly());
				fpyChart.setTitleTop(filterLocal.toTitle());
				fpyChart.setTypeTimeChart(filterLocal.getTypeTimeChart());
				fpyChart.setTimeInterval(timeInterval.getTime_interval());
				
				fpyChart.setInputFilterFrom(filterLocal.toInputFilterFrom());
				fpyChart.setInputFilterTo(filterLocal.toInputFilterTo());
				
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
	
	
	} // end main
	
	
	private static List<String> categories(FpyChartFilter filterLocal, FpyChartFilter filterInput)  throws ParseException {
		List<String> xAix =  new ArrayList<String>();

	DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern("yyyyMMddHH");
	LocalDateTime     periodBegin = filterLocal.getFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    LocalDateTime     periodEnd   = filterLocal.getTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    	//System.out.println(" periodBegin " + periodBegin);
    	//System.out.println(" periodEnd " + periodEnd);

        for(String t : filterLocal.getListTypeTimeChart()){
			System.out.println(t);

					if(t.equals("YEARLY")){
						System.out.println("I am UPON Yearly");

						if(filterInput.getWork_id_begin() != null){
							//String dateString = "2018-10-14 23:59:59";
							//LocalDateTime today = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
							String work_id_begin = filterInput.getWork_id_begin();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
							Date dateFrom = sdf.parse(work_id_begin);
							filterLocal.setFrom(dateFrom);

						} else {
							getIntervalDateYearly(filterLocal);
						} // end else

						//Filter Begin
						System.out.println("filterLocal.getFrom() : " + filterLocal.getFrom());

						DateTimeFormatter dateFormatKey = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
						DateTimeFormatter KeyYear = DateTimeFormatter.ofPattern("yyyy", Locale.ENGLISH);

				        LocalDate c_periodBegin = filterLocal.getFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				        System.out.println("localDate : " + c_periodBegin);

				        LocalDate c_periodEnd = filterLocal.getTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				        System.out.println("localDate : " + c_periodEnd);

						boolean getOut = false;
						while (!getOut) {

							System.out.println(c_periodBegin.getMonth());
							System.out.println(dateFormatKey.format(c_periodBegin)+KeyYear.format(c_periodBegin));

							String result = dateFormatKey.format(c_periodBegin)+"/"+KeyYear.format(c_periodBegin);
							xAix.add(result);


							c_periodBegin = c_periodBegin.plusMonths(1);

							System.out.println(c_periodBegin);

							Period period = Period.between(c_periodBegin, c_periodEnd);

							System.out.println(period.getMonths());

							if(period.getMonths() <= 0){
								String result2 = dateFormatKey.format(c_periodBegin)+"/"+KeyYear.format(c_periodBegin);
								xAix.add(result2);
								getOut = true;
								System.out.println("The target is ok!!...");
							}
						}// end while

					} // end YEARLY



					if(t.equals("MONTHLY")){
						System.out.println("I am UPON MONTHLY");

						if(filterInput.getWork_id_begin() != null){

							String work_id_begin = filterInput.getWork_id_begin();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

							Date dateFrom = sdf.parse(work_id_begin);
							filterLocal.setFrom(dateFrom);

						} else {
							getIntervalDateMonthly(filterLocal);

						}

						//Filter Begin
						System.out.println("filterLocal.getFrom() : " + filterLocal.getFrom());

						DateTimeFormatter dateFormatKey = DateTimeFormatter.ofPattern("d");
						LocalDateTime c_periodBegin = filterLocal.getFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
						LocalDateTime c_periodEnd   = filterLocal.getTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

						boolean getOut = false;
						while (!getOut) {

							System.out.println(c_periodBegin.getDayOfMonth());
							System.out.println(dateFormatKey.format(c_periodBegin));

							String c = "D"+dateFormatKey.format(c_periodBegin);
							System.out.println(c);

							xAix.add(c);

							c_periodBegin = c_periodBegin.plusDays(1);

							System.out.println(c_periodBegin);
							Duration duration = Duration.between(c_periodBegin, c_periodEnd);

							System.out.println(duration.toDays());

							if(duration.toDays() <= 0){
								String c2 = "D"+dateFormatKey.format(c_periodBegin);
								System.out.println(c2);

								xAix.add(c2);
								getOut = true;
								System.out.println("The target is ok!!...");
							}
						}// end while

					}// end MONTHY


					if(t.equals("WEEK_OF_YEAR")){
						System.out.println("I am UPON WEEK_OF_YEAR");

						if(filterInput.getWork_id_begin() != null){

							String work_id_begin = filterInput.getWork_id_begin();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

							Date dateFrom = sdf.parse(work_id_begin);
							filterLocal.setFrom(dateFrom);

						} else {
							getIntervalDateWeekOfYear(filterLocal);
						}
						//Filter Begin
						System.out.println("filterLocal.getFrom() : " + filterLocal.getFrom());

						DateTimeFormatter dateFormatKey = DateTimeFormatter.ofPattern("yyyy");
						
						LocalDateTime c_periodBegin = filterLocal.getFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
						LocalDateTime c_periodEnd   = filterLocal.getTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

						boolean getOut = false;
						while (!getOut) {

							System.out.println(c_periodBegin.format(DateTimeFormatter.ISO_WEEK_DATE).substring(0, 8).replace("-", ""));

							Long weeks = ChronoUnit.WEEKS.between(c_periodBegin, c_periodEnd);
							System.out.println(weeks.toString());

							String key = c_periodBegin.format(DateTimeFormatter.ISO_WEEK_DATE).substring(0, 8).replace("-", "");
							xAix.add(key);

							c_periodBegin = c_periodBegin.plusWeeks(1);

							if(weeks <= 0L){
								String key2 = c_periodBegin.format(DateTimeFormatter.ISO_WEEK_DATE).substring(0, 8).replace("-", "");
								xAix.add(key2);
								getOut = true;
								System.out.println("The target is ok!!...");
							}
						}// end while

					}// end WEEK_OF_YEAR




					if(t.equals("WEEK_OF_MONTH")){
						System.out.println("I am UPON WEEK_OF_MONTH");

						if(filterInput.getWork_id_begin() != null){

							String work_id_begin = filterInput.getWork_id_begin();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

							Date dateFrom = sdf.parse(work_id_begin);
							filterLocal.setFrom(dateFrom);

						} else {
							getIntervalDateWeekOfMonth(filterLocal);

						} // end else


						//Filter Begin
						System.out.println("filterLocal.getFrom() : " + filterLocal.getFrom());

						DateTimeFormatter keyFormat = DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH);
						LocalDateTime c_periodBegin = filterLocal.getFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
						LocalDateTime c_periodEnd   = filterLocal.getTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

						boolean getOut = false;
						while (!getOut) {

							System.out.println(c_periodBegin);
							System.out.println(c_periodBegin.getDayOfMonth());

							String key = keyFormat.format(c_periodBegin);

							xAix.add(key);

							c_periodBegin = c_periodBegin.plusDays(1);

							System.out.println(c_periodBegin);
							Duration duration = Duration.between(c_periodBegin, c_periodEnd);

							System.out.println(duration.toHours());

							if(duration.toDays() <= 0){
								getOut = true;
								System.out.println("The target is ok!!...");
							}
						}// end while
					}// end WEEK_OF_MONTH


					if(t.equals("DAILY")){
						System.out.println("I am UPON DAILY");

						if(filterInput.getWork_id_begin() != null){
							String work_id_begin = filterInput.getWork_id_begin();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
							Date dateFrom = sdf.parse(work_id_begin);
							filterLocal.setFrom(dateFrom);

						} else {
							getIntervalDateDaily(filterLocal);
						}

						//Filter Begin
						System.out.println("filterLocal.getFrom() : " + filterLocal.getFrom());

						DateTimeFormatter keyFormat = DateTimeFormatter.ofPattern("HH");
						LocalDateTime c_periodBegin = filterLocal.getFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
						LocalDateTime c_periodEnd   = filterLocal.getTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

						boolean getOut = false;
						while (!getOut) {

							String key = keyFormat.format(c_periodBegin)+"H";

							xAix.add(key);

							c_periodBegin = c_periodBegin.plusHours(1);

							System.out.println(c_periodBegin);
							Duration duration = Duration.between(c_periodBegin, c_periodEnd);

							System.out.println(duration.toHours());

							if(duration.toHours() <= 0){
								String key2 = keyFormat.format(c_periodBegin)+"H";
								xAix.add(key2);
								getOut = true;
								System.out.println("The target is ok!!...");
							}
						}// end while


					}// end DAILY





        } // for





		return xAix;
	} // end categorias



	private static List<Double> fillData( FpyChartFilter             filterLocal
			                             ,FpyChartFilter             filterInput
			                             ,List<String>               dataTypeTime
			                             ,List<DataPointYearly>      dataYearly
			                             ,List<DataPointMonthly>     dataMonthly
			                             ,List<DataPointWeekOfYear>  dataWeekOfYear
			                             ,List<DataPointWeekly>      dataWeekOfMonth
			                             ,List<DataPoint>            dataDaily
			                             ) throws ParseException{

		
		//"YEARLY", "MONTHLY",  "WEEK_OF_YEAR",	 "WEEK_OF_MONTH", "DAILY"  		
	    List<Double> valuesPoint =  new ArrayList<Double>();
	
		Map<String, Double> mapYearly = new HashMap<String, Double>();
		for(DataPointYearly pointYearly : dataYearly){
			mapYearly.put(pointYearly.getKey_date_yearly(), pointYearly.getValue());
		}
		System.out.println(" ************************************");
		mapYearly.forEach((k,v)->System.out.println("keyTime : " + k + " values : " + v));

		
		Map<String, Double> mapMonthly = new HashMap<String, Double>();
		for(DataPointMonthly pointMonthly : dataMonthly){
			mapMonthly.put(pointMonthly.getKey_date_monthly(), pointMonthly.getValue());
		}
		System.out.println(" ************************************");
		mapMonthly.forEach((k,v)->System.out.println("keyTime : " + k + " values : " + v));


		Map<String, Double> mapWeekOfYear = new HashMap<String, Double>();
		for(DataPointWeekOfYear pointWeekOfYear : dataWeekOfYear){
			mapWeekOfYear.put(pointWeekOfYear.getKey_date_week_of_year(), pointWeekOfYear.getValue());
		}
		System.out.println(" ************************************");
		mapWeekOfYear.forEach((k,v)->System.out.println("keyTime : " + k + " values : " + v));
		
		
		Map<String, Double> mapWeekOfMonth = new HashMap<String, Double>();
		for(DataPointWeekly pointWeekOfMonth : dataWeekOfMonth){
			mapWeekOfMonth.put(pointWeekOfMonth.getKey_date_weekly(), pointWeekOfMonth.getValue());
		}
		System.out.println(" ************************************");
		mapWeekOfMonth.forEach((k,v)->System.out.println("keyTime : " + k + " values : " + v));
		

		Map<String, Double> mapDaily = new HashMap<String, Double>();
		for(DataPoint pointDaily : dataDaily){
			mapDaily.put(pointDaily.getMesDataHora(), pointDaily.getValue());
		}
		System.out.println(" ************************************");
		mapDaily.forEach((k,v)->System.out.println("keyTime : " + k + " values : " + v));
		

		
		for(String t : filterLocal.getListTypeTimeChart()){
			System.out.println(t);
			
					if(t.equals("YEARLY")){
						System.out.println("I am UPON Yearly");
						
						if(filterInput.getWork_id_begin() != null){
							//String dateString = "2018-10-14 23:59:59";
							//LocalDateTime today = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
							String work_id_begin = filterInput.getWork_id_begin();	
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
							Date dateFrom = sdf.parse(work_id_begin);
							filterLocal.setFrom(dateFrom);
							
						} else {
							getIntervalDateYearly(filterLocal);
						} // end else
						
						//Filter Begin
						System.out.println("filterLocal.getFrom() : " + filterLocal.getFrom());
						
						DateTimeFormatter dateFormatKey = DateTimeFormatter.ofPattern("yyyyMM");
						
				        LocalDate periodBegin = filterLocal.getFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				        System.out.println("localDate : " + periodBegin);
				       
				        LocalDate periodEnd = filterLocal.getTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				        System.out.println("localDate : " + periodEnd);

						boolean getOut = false;
						while (!getOut) {
							
							System.out.println(periodBegin.getMonth());
							
							String key = dateFormatKey.format(periodBegin);
							System.out.println("key : " + key);
							Double result =  mapYearly.get(key);
							System.out.println("result : " + result);
							
							if(result != null){
								valuesPoint.add(result);
							} else {
								valuesPoint.add(0.0);
							}
							
							periodBegin = periodBegin.plusMonths(1);
							
							System.out.println(periodBegin);
							
							Period period = Period.between(periodBegin, periodEnd);
							
							System.out.println(period.getMonths());
							
							if(period.getMonths() <= 0){
								System.out.println(periodBegin.getMonth());
								getOut = true;
								System.out.println("The target is ok!!...");
							}
						}// end while
						
					} // end YEARLY
					
					
					if(t.equals("MONTHLY")){
						System.out.println("I am UPON MONTHLY");
						
						if(filterInput.getWork_id_begin() != null){
							
							String work_id_begin = filterInput.getWork_id_begin();	
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
							
							Date dateFrom = sdf.parse(work_id_begin);
							filterLocal.setFrom(dateFrom);
							
						} else {
							getIntervalDateMonthly(filterLocal);
							
						}
						
						//Filter Begin
						System.out.println("filterLocal.getFrom() : " + filterLocal.getFrom());
						
						DateTimeFormatter dateFormatKey = DateTimeFormatter.ofPattern("yyyyMMdd");
						LocalDateTime periodBegin = filterLocal.getFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
						LocalDateTime periodEnd   = filterLocal.getTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
						
						boolean getOut = false;
						while (!getOut) {
							
							System.out.println(periodBegin.getDayOfMonth());
							
							String key = dateFormatKey.format(periodBegin);
							System.out.println("key : " + key);
							Double result =  mapMonthly.get(key);
							System.out.println("result : " + result);
							
							if(result != null){
								valuesPoint.add(result);
							} else {
								valuesPoint.add(0.0);
							}
							
							periodBegin = periodBegin.plusDays(1);
							
							System.out.println(periodBegin);
							Duration duration = Duration.between(periodBegin, periodEnd);
							
							System.out.println(duration.toDays());
							
							if(duration.toDays() <= 0){
								getOut = true;
								System.out.println("The target is ok!!...");
							}
						}// end while
						
					}// end MONTHY
					
					
					
					if(t.equals("WEEK_OF_YEAR")){
						System.out.println("I am UPON WEEK_OF_YEAR");

						if(filterInput.getWork_id_begin() != null){
							
							String work_id_begin = filterInput.getWork_id_begin();	
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
							
							Date dateFrom = sdf.parse(work_id_begin);
							filterLocal.setFrom(dateFrom);
							
						} else {
							getIntervalDateWeekOfYear(filterLocal);
						} // end else


						//Filter Begin
						System.out.println("filterLocal.getFrom() : " + filterLocal.getFrom());
						
						DateTimeFormatter dateFormatKey = DateTimeFormatter.ofPattern("yyyy");
						LocalDateTime periodBegin = filterLocal.getFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
						LocalDateTime periodEnd   = filterLocal.getTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
						
						boolean getOut = false;
						while (!getOut) {
							
							System.out.println(periodBegin.format(DateTimeFormatter.ISO_WEEK_DATE).substring(0, 8).replace("-", ""));

							Long weeks = ChronoUnit.WEEKS.between(periodBegin, periodEnd);
							System.out.println(weeks.toString());
							
							String key = periodBegin.format(DateTimeFormatter.ISO_WEEK_DATE).substring(0, 8).replace("-", "");
							System.out.println("key : " + key);
							Double result =  mapMonthly.get(key);
							System.out.println("result : " + result);
							
							if(result != null){
								valuesPoint.add(result);
							} else {
								valuesPoint.add(0.0);
							}
							
							periodBegin = periodBegin.plusWeeks(1);
							
							if(weeks <= 0L){
								getOut = true;
								System.out.println("The target is ok!!...");
							}
						}// end while
					
					
					
					}// end WEEK_OF_YEAR
					
					
					if(t.equals("WEEK_OF_MONTH")){
						System.out.println("I am UPON WEEK_OF_MONTH");
						
						if(filterInput.getWork_id_begin() != null){
							
							String work_id_begin = filterInput.getWork_id_begin();	
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
							
							Date dateFrom = sdf.parse(work_id_begin);
							filterLocal.setFrom(dateFrom);
							
						} else {
							getIntervalDateWeekOfMonth(filterLocal);
							
						} // end else

						
						//Filter Begin
						System.out.println("filterLocal.getFrom() : " + filterLocal.getFrom());
						
						DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern("yyyyMMdd");
						LocalDateTime periodBegin = filterLocal.getFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
						LocalDateTime periodEnd   = filterLocal.getTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
						
						boolean getOut = false;
						while (!getOut) {
							
							System.out.println(periodBegin);
							System.out.println(periodBegin.getDayOfMonth());
							
							String key = dateFormat8.format(periodBegin);
							System.out.println("key : " + key);
							Double result =  mapDaily.get(key);
							System.out.println("result : " + result);
							
							if(result != null){
								valuesPoint.add(result);
							} else {
								valuesPoint.add(0.0);
							}
							
							periodBegin = periodBegin.plusDays(1);
							
							System.out.println(periodBegin);
							Duration duration = Duration.between(periodBegin, periodEnd);
							
							System.out.println(duration.toHours());
							
							if(duration.toDays() <= 0){
								getOut = true;
								System.out.println("The target is ok!!...");
							}
						}// end while
					}// end WEEK_OF_MONTH
					
					
					
					if(t.equals("DAILY")){
						System.out.println("I am UPON DAILY");
						
						if(filterInput.getWork_id_begin() != null){
							String work_id_begin = filterInput.getWork_id_begin();	
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
							Date dateFrom = sdf.parse(work_id_begin);
							filterLocal.setFrom(dateFrom);
							
						} else {
							getIntervalDateDaily(filterLocal);
						}
						
						//Filter Begin
						System.out.println("filterLocal.getFrom() : " + filterLocal.getFrom());
						
						DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern("yyyyMMddHH");
						LocalDateTime periodBegin = filterLocal.getFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
						LocalDateTime periodEnd   = filterLocal.getTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
						
						boolean getOut = false;
						while (!getOut) {
							
							String key = dateFormat8.format(periodBegin);
							System.out.println("key : " + key);
							Double result =  mapDaily.get(key);
							System.out.println("result : " + result);
							
							if(result != null){
								valuesPoint.add(result);
							} else {
								valuesPoint.add(0.0);
							}
							
							periodBegin = periodBegin.plusHours(1);
							
							System.out.println(periodBegin);
							Duration duration = Duration.between(periodBegin, periodEnd);
							
							System.out.println(duration.toHours());
							
							if(duration.toHours() <= 0){
								getOut = true;
								System.out.println("The target is ok!!...");
							}
						}// end while
						
						
					}// end DAILY
					
					
					
				

		
		
		} //  filterLocal.getTypeTimeChart()

		
		return valuesPoint;
} // end fillData

		
		
		
		
		


	private static List<String> fillNameSeriesMap(Map<String, String> mapDescricao) {
	   	 List<String> t = new ArrayList<String>();
	   	 for(Map.Entry<String, String> h : mapDescricao.entrySet()){
	   		t = Arrays.asList(h.getKey(), h.getValue());
	   	 }
	   	 return t;
	}
	
	
	private static void getIntervalDateWeekOfMonth(FpyChartFilter filterLocal) {
		LocalDateTime today = LocalDateTime.now();
		System.out.println(today.with(previousOrSame(DayOfWeek.MONDAY)).with(LocalTime.MIN) );
		System.out.println(today.with(nextOrSame(DayOfWeek.SUNDAY)).with(LocalTime.MAX) );
		
		LocalDateTime ldFrom = today.with(previousOrSame(DayOfWeek.MONDAY)).with(LocalTime.MIN);
		LocalDateTime ldTo   = today.with(nextOrSame(DayOfWeek.SUNDAY)).with(LocalTime.MAX);
		
		//Extra
		for(DayOfWeek day : DayOfWeek.values()){
			System.out.println(ldFrom.with(nextOrSame(day)));
		}

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

	private static void getIntervalDateDaily(FpyChartFilter filterLocal) {
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

	private static void getIntervalDateWeekOfYear(FpyChartFilter filterLocal) {
		System.out.println("WEEK_OF_YEAR  *************************8" );
		LocalDateTime today = LocalDateTime.now();
		//String dateString = "2017-11-14 23:59:59";
		//LocalDateTime today = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		//System.out.println(today.format(DateTimeFormatter.ISO_WEEK_DATE).substring(0, 8).replace("-", ""));

		//System.out.println(today.with(firstDayOfYear()).with(LocalTime.MIN));
	    //System.out.println(today.with(lastDayOfYear()).with(LocalTime.MAX) );
		
		LocalDateTime ldFrom         = today.with(firstDayOfYear()).with(LocalTime.MIN);
		LocalDateTime ldToTruncated  = today.with(lastDayOfYear()).with(LocalTime.MAX);
		
		System.out.println(today.minusWeeks(1).with(nextOrSame(DayOfWeek.MONDAY)).with(LocalTime.MIN) ); //2017-04-10T00:00
		System.out.println(today.minusWeeks(1).with(nextOrSame(DayOfWeek.SATURDAY)).with(LocalTime.MAX) ); //2017-04-15T23:59:59.999999999
	
		//LocalDateTime ldFrom         = today.minusWeeks(1).with(nextOrSame(DayOfWeek.MONDAY)).with(LocalTime.MIN);
		//LocalDateTime ldToTruncated  = today.minusWeeks(1).with(nextOrSame(DayOfWeek.SATURDAY)).with(LocalTime.MAX);
		
		LocalDateTime ldTo           = ldToTruncated.truncatedTo(ChronoUnit.SECONDS);
		
		System.out.println(ldTo.format(DateTimeFormatter.ISO_WEEK_DATE).substring(0, 8).replace("-", ""));
		
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

	private static void getIntervalDateMonthly(FpyChartFilter filterLocal) {
		LocalDateTime today = LocalDateTime.now();
		//System.out.println(today.withDayOfMonth(1).with(LocalTime.MIN));
		//System.out.println(today.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX));
		
		//LocalDateTime ldFrom = today.withDayOfMonth(1).with(LocalTime.MIN);
		//LocalDateTime ldTo   = today.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
		
		//String asIsoWeekDate = ldTo.format(DateTimeFormatter.ofPattern("yyyyw"));
		//System.out.println(asIsoWeekDate );

		System.out.println(today.minusDays(5).with(LocalTime.MIN));  //2017-04-11T00:00
		System.out.println(today.with(LocalTime.MAX)); //2017-04-16T23:59:59.999999999
		
		LocalDateTime ldFrom = today.minusDays(5).with(LocalTime.MIN);
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

	private static void getIntervalDateYearly(FpyChartFilter filterLocal) {
		LocalDateTime today = LocalDateTime.now();
		//System.out.println(today.with(firstDayOfYear()).with(LocalTime.MIN));
		//System.out.println(today.with(lastDayOfYear()).with(LocalTime.MAX) );
		//LocalDateTime ldFrom         = today.with(firstDayOfYear()).with(LocalTime.MIN);
		//LocalDateTime ldToTruncated  = today.with(lastDayOfYear()).with(LocalTime.MAX);
		
		
		System.out.println(today.with(firstDayOfYear()).with(LocalTime.MIN));  //2017-01-01T00:00
		System.out.println(today.with(firstDayOfYear()).with(LocalTime.MIN).plusMonths(2).with(lastDayOfMonth()).with(LocalTime.MAX)); //2017-03-31T23:59:59.999999999
		
		LocalDateTime ldFrom         = today.with(firstDayOfYear()).with(LocalTime.MIN);
		LocalDateTime ldToTruncated  = today.with(firstDayOfYear()).with(LocalTime.MIN).plusMonths(2).with(lastDayOfMonth()).with(LocalTime.MAX);
		
		LocalDateTime ldTo           = ldToTruncated.truncatedTo(ChronoUnit.SECONDS);

		String keyFilter =ldFrom.format(DateTimeFormatter.ofPattern("yyyyMM"));
		System.out.println(keyFilter);
		System.out.println( " insert incategories : " + ldFrom.getMonth());
		
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

} // end GroupAllReultObjectJson

















