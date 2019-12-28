package com.hc.utils.string;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IncreaseString {
	
	private static String date;
	private static Long increase = 1L;
	/**
	 * 根据当前日期获取自增字符串并且补零
	 * digits：字符串的位数
	 * dateString：当前日期
	 * ***/
	public static String increaseNum(int digits,String dateString) {
		Long increases = 1L;
		if(dateString.equals(date)){
			increases = increase = increase + 1;
		}else{
			date = dateString;
			increases = increase = 1L;
		}
		
		String increaseString = increases.toString();
		int increaseLength = increaseString.length();
		if(increaseLength<digits){
			for(int i = 0;i<digits-increaseLength;i++){
				increaseString = "0"+increaseString;
			}
		}else{
			increaseString = null;
		}
		return increaseString;
	}
public static void main(String[] args) {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	Date date = new Date();
	String dateString = simpleDateFormat.format(date);
	for(int i = 0;i<100;i++){
		System.out.println(increaseNum(6,dateString));
	}
}
}
