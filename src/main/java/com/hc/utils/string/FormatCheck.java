package com.hc.utils.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FormatCheck {
	
	public static boolean isEmail(String s) {    
	    return Pattern.compile("\\w+@{1}\\w+\\.{1}\\w+").matcher(s).matches();    
	}    
	
	/**
	 * 判断是否手机号
	 * @param phone
	 * @return
	 */
	public static boolean isMobilePhone(String phone) {
	    boolean flag=true;
	    String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
	    if (phone.length() != 11) {
	        flag= false;
	    } else {
	        Pattern p = Pattern.compile(regex);
	        Matcher m = p.matcher(phone);
	        flag = m.matches();
	    }
	    return flag;
	}
		
	public static void main(String[] args) {
		System.out.println(isEmail("28088860"));
	}
}
