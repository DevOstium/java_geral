package com.geral.fox.others;


import java.text.SimpleDateFormat;
import java.util.Date;

public class StringSubString {

	public static void main(String[] args) {
	
		String txtFile ="--VARIABLE_HERE--^XA^LH20,18^FO90,0^BXN,6,200,18,18,6,_^FD--PPID--^FS^FO220,0^A5N,30^FDMade In Brasil^FS^FO220,0^A5N,30^FDDP/N 614WK^FS^FO220,0^A5N,30^FD--PPID7--^FS^FO220,35^A5N,30^FD--PPID8--^FS^XZDP/N614WKBR0614WK108497300001A00";

		String newPPID = "BR0614WK108497300001A00";
		
		 String ret = txtFile;

	        Date date = new Date();
	        
	        SimpleDateFormat sdfMM = new SimpleDateFormat("MM");
	        SimpleDateFormat sdfy = new SimpleDateFormat("yy");
	        String lastCharYear = sdfy.format(date).substring(1);
	        
	        String p1 = "";
	        String p2 = "";
	        String p3 = "";
	        String p4 = "";
	        String p5 = "";
	        String p6 = "";
	 
	        String p7 = "";
	        String p8 = "";
	        String p9 = "";
	        String p10 = "";
	        String p11 = "";
	        String p12 = "";
	        String p13 = "";
	        String p14 = "";
	        String p15 = "";
	        
	          
			
	        //String newPPID = "BR-0614WK-10849-";
	        //String newPPID = "730-0001-A00";

	        p7 = newPPID.substring(0,2);
	        p8 = newPPID.substring(2,8);
	        p9 = newPPID.substring(8,13);
	        
	        p10 = newPPID.substring(13,16);
	        p11 = newPPID.substring(16,20);
	        p12 = newPPID.substring(20,23);
	        
	        //System.out.println("part7 : " + p7+"-"+p8+"-"+p9+"-");
	       // System.out.println("part7 : " + p10+"-"+p11+"-"+p12);
	        
	        p1 = newPPID.substring(0,2);
	        p2 = newPPID.substring(2,8);
	        p3 = newPPID.substring(8,13);
	        p4 = newPPID.substring(13,16);
	        p5 = newPPID.substring(16,20);
	        p6 = newPPID.substring(20,newPPID.length());
	        
	        
	        String part1 = p1+"-"+p2+"-"+p3+"-";
	        String part2 = p4+"-"+p5+"-"+p6;
	        
	       
	        String part7 =p7+"-"+p8+"-"+p9+"-";
	        String part8 =p10+"-"+p11+"-"+p12;
	       
	        System.out.println(part7);
	        System.out.println(part8);
	        
	        ret = ret.replaceAll("--PPID--", newPPID);
	        ret = ret.replaceAll("--PPID1--", part1);
	        ret = ret.replaceAll("--PPID2--", part2);
	        
	        ret = ret.replaceAll("--PPID7--", part7);
	        ret = ret.replaceAll("--PPID8--", part8);
	 
	      
	        
	        ret = ret.replaceAll("--PN--", p2.substring(1));
	      // ret = ret.replaceAll("--FRU--", fru);
	        ret = ret.replaceAll("--DATECODELENOVO--", lastCharYear + sdfMM.format(date));
	        
	        System.out.println(" ret �: " + ret);
	        

	}

}
