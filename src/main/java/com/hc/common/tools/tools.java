package com.hc.common.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class tools {

	
	public static String getAPIresponseDateTime()
	{
		Date nowDate=new Date();
//		SimpleDateFormat formate=new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa",Locale.ENGLISH);
		SimpleDateFormat formate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowDatStr=formate.format(nowDate);
		return nowDatStr;
	}
}
