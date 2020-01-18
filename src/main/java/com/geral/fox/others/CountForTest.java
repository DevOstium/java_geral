package com.geral.fox.others;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.foxconn.tdk.dao.fpy.FPYDAO;
import br.com.foxconn.tdk.model.fpy.DataPoint;
import br.com.foxconn.tdk.model.fpy.FpyChart;
import br.com.foxconn.tdk.model.fpy.FpyChartFilter;

public class CountForTest {

	
	public static void main(String[] args) {
	
		Map<String, Double> dataMap = new HashMap<>(); 
		

		FPYDAO dao = new FPYDAO();
		FpyChart fpyChart = new FpyChart();
		FpyChartFilter f = new FpyChartFilter();
		
		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		
		c.add(Calendar.DAY_OF_MONTH, -2);
		f.setFrom(c.getTime());
		
		c.add(Calendar.DAY_OF_MONTH, 2);
		f.setTo(c.getTime());
		
		
		f.setGroup_name("ICT");
		f.setModelNameList(Arrays.asList("HD5W2", "K8N4N"));
		
		List<DataPoint> data = dao.getDailyLineSeriesByFilter(f);
		System.out.println(data);	
		for (DataPoint dp : data) {
			dataMap.put(dp.getTime(), dp.getValue());
			System.out.println(dp.getTime() +  dp.getValue());
		}

				
		//loop a Map
		for (Map.Entry<String, Double> entry : dataMap.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		}

		//Java 8 only, forEach and Lambda
		dataMap.forEach((k,v)->System.out.println("Key : " + k + " Value : " + v));
		
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(f.getFrom());
		
		System.out.println(calendar.getTime());
		
		List<Double> ret = new ArrayList<>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		
		boolean getOut = false;
		while (!getOut) {
			
			String hour = sdf.format(calendar.getTime());
			//System.out.println(dataMap.get(hour));
			Double result = dataMap.get(hour);
			//System.out.println(result);
			
			if (result == null) {
				ret.add(0.0);
			} else {
				ret.add(result);
			}
			
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			
			if (calendar.getTimeInMillis() == f.getTo().getTime()) {
				getOut = true;
			}
			
		}
		
		
	}
}
