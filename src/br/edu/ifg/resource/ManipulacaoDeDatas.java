package br.edu.ifg.resource;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ManipulacaoDeDatas {
	
    public  static java.sql.Date formataData(String data) throws Exception { 
 		if (data == null || data.equals(""))
 			return null;
 		
         java.sql.Date date = null;
         try {
             DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
             date = new java.sql.Date( ((java.util.Date)formatter.parse(data)).getTime() );
         } catch (ParseException e) {            
             throw e;
         }
         return date;
 	}
    public static String inverteData (String data) throws ParseException {
    	data = data.replace("-", "/");
    	DateFormat formatUS = new SimpleDateFormat("yyyy/mm/dd");
    	java.util.Date date = formatUS.parse(data);
    	
    	DateFormat formatBR = new SimpleDateFormat("dd/mm/yyyy");
    	String dateFormated = formatBR.format(date);
    	data = dateFormated.toString();
		return data;
    	
    	
    }
    public static Integer  subtraiDatas(String data) throws ParseException {
    	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
    	java.util.Date dataNascimento =  formato.parse(data);
    	GregorianCalendar hj=new GregorianCalendar();
		GregorianCalendar nascimento=new GregorianCalendar();
		
			nascimento.setTime(dataNascimento);
		
		int anohj=hj.get(Calendar.YEAR);
		int anoNascimento=nascimento.get(Calendar.YEAR);
		return new Integer(anohj-anoNascimento);
    }
//    public static void main(String[] args) throws ParseException {
//		System.out.println(subtraiDatas("30/12/2000"));
//	}
}
