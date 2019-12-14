package com.hc.controller.voice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

public class HttpClientRequest {

	
	Logger log=Logger.getLogger(getClass());
	private static final String  CHARSET_ENCODE="utf-8";
	
	public String requestVoiceServer(String serverUrl)
	{
		 HttpClient client=new HttpClient();
		 PostMethod post=new PostMethod(serverUrl);
		 post.addRequestHeader("Http-Code", "UTF-8");
		 client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
		 String rtnString="";
		 BufferedReader reader=null;
		 try
		 {
			 post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());///不成功則重試三次
			 client.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, CHARSET_ENCODE);
			 
			int status = client.executeMethod(post);
			if(status==200)
			{
				reader = new BufferedReader(new InputStreamReader(
						post.getResponseBodyAsStream(), "ISO-8859-1"));
				String tmp = null;
				String htmlRet = "";

				while ((tmp = reader.readLine()) != null) {
					htmlRet += tmp + "\r\n";
				}

				rtnString = new String(htmlRet.getBytes("ISO-8859-1"), "utf-8");
			}
			else
			{
				throw new RuntimeException("連接服務器異常");
			}

		 }
		 catch(Exception e)
		 {
			 log.error("PostPureDataToServer getDataFromServer:"+e);
//			 SCOptionPane.showMessageDialog(GlobalService.getMainForm(),"連接關閉!重試后問題仍存在則重新登陸!", "提示", SCOptionPane.ERROR_MESSAGE);
			 rtnString=null;
		 }
		 finally
		 {
			 post.releaseConnection();
			 if(reader!=null)
			 {
				 try {
					reader.close();
				} catch (IOException e) {
					log.error(getClass()+" getDataFromServer e:"+e);
					e.printStackTrace();
				}
			 }
			
		 }
		 return rtnString;
	}
}
