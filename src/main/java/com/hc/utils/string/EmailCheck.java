package com.hc.utils.string;

import java.util.regex.Pattern;

public final class EmailCheck {
	
	public static boolean isEmail(String s) {    
	    return Pattern.compile("\\w+@{1}\\w+\\.{1}\\w+").matcher(s).matches();    
	}    
	
	public static void main(String[] args) {
		System.out.println(isEmail("28088860"));
	}
}
