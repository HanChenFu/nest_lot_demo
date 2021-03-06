package com.hc.common.tools;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;


public class DownloadImage {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//        download("http://www.sz.gov.cn/saqscjdglj/zwgk/xxgkmu/qt/gzdt/201912/W020191217520095740604.png","D:\\upload\\SDSPage");
    }

    public static String download(String urlPath,String savePath) throws Exception {
       try {
	    	// 构造URL
	        URL url = new URL(urlPath);
	        // 打开连接
	        URLConnection con = url.openConnection();
	        //设置请求超时为5s
	        con.setConnectTimeout(10*1000);
	        // 输入流
	        InputStream is = con.getInputStream();
	        // 1K的数据缓冲
	        byte[] bs = new byte[1024];
	        // 读取到的数据长度
	        int len;
	        // 输出的文件流
	        File sf=new File(savePath);
	        if(!sf.exists()){
	            sf.mkdirs();
	        }
	//      int randomNo=(int)(Math.random()*1000000);
	        String filename=urlPath.substring(urlPath.lastIndexOf("/")+1,urlPath.length());//获取服务器上图片的名称
	//      filename=new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())+randomNo+filename;//时间+随机数防止重复
	        OutputStream os = null;
	        try {
	        	os = new FileOutputStream(sf.getPath()+"\\"+filename);
//	        String virtualPath="/upload/SDSPage/"+filename;//存入数据库的虚拟路径
	        	// 开始读取
	        	while ((len = is.read(bs)) != -1) {
	        		os.write(bs, 0, len);
	        	}
	        	// 完毕，关闭所有链接
	        	os.close();
	        	is.close();
			} catch (Exception e) {
				System.err.println("上传图片异常！");
				e.printStackTrace();
			}finally {
				// 完毕，关闭所有链接
				if(os!=null) {
		        	os.close();
				}
				if(is!=null) {
					is.close();
				}
			}
       } catch (Exception e) {
    	   System.err.println("上传图片异常！");
    	   System.err.println("urlPath:"+urlPath);
    	   System.err.println("savePath:"+savePath);
    	   e.printStackTrace();
   	}
        return "";
    }

}