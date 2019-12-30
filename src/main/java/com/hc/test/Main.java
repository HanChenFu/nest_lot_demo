package com.hc.test;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import sun.misc.BASE64Decoder;


//import io.netty.handler.codec.base64.Base64Decoder;

public class Main {
	
	public static  void  main(String [] args) {
		
//		 //国家应急中心漏洞公告页面
//		 String leakUrl = "https://www.cert.org.cn/publish/main/9/index.html";
//		 //处理第一页的源码
//		 Document leakListDoc = sendHttp(leakUrl);
//		 int page = 1;
//		 System.out.println("处理第"+page+"页数据");
//		 solvePageData(leakListDoc);
//		 getPageCode(leakListDoc,page);
 
//----------------------------------------------------------------------------------
		 
//        String resource = "mybatis-config.xml"; //定义配置文件路径
//        InputStream inputStream = null;
//        try {
//            inputStream = Resources.getResourceAsStream(resource);//读取配置文件
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//注册mybatis 工厂
//
//        SqlSession sqlSession = sqlSessionFactory.openSession();//得到连接对象

//        MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);//从mybatis中得到dao对象


        int start;//每页多少条
        int total = 0;//记录数
        int end = 10;//总共9979条数据
        for (start  = 0; start <= end; start += 20)  {
            try {

//              String address = "https://Movie.douban.com/j/new_search_subjects?sort=U&range=0,10&tags=&start=" + start;
//              String address = "http://yjgl.sz.gov.cn/zwgk/xxgkmu/qt/gzdt/"+" 201912/t20191217_18936590.htm";
//              http://yjgl.sz.gov.cn/zwgk/xxgkmu/qt/tzgg/201912/t20191217_18936778.htm
//              ./201912/t20191217_18936590.htm
//            	String address =  "http://yjgl.sz.gov.cn/zwgk/xxgkmu/qt/gzdt/201912/t20191217_18936590.htm";
//            	http://yjgl.sz.gov.cn/zwgk/xxgkmu/qt/gzdt/W020191217520096047309.png
//            	String address =  "http://yjgl.sz.gov.cn/zwgk/xxgkmu/qt/gzdt/201912/t20191213_18931038.htm";
            	
            	String address =  "http://www.sz.gov.cn/saqscjdglj/zwgk/xxgkmu/qt/gzdt/201912/t20191217_18936590.htm";
//            		http://www.sz.gov.cn/saqscjdglj/zwgk/xxgkmu/qt/gzdt/201912/W020191217520095740604.png
                String dayLine = new GetJson().getHttpJson2(address, 1);
//              System.out.println("dayLine:" + dayLine);

                org.jsoup.nodes.Document d = Jsoup.parse(dayLine);
                
                System.out.println("=========="+d);
                solvePageData(d);
                
//                System.out.println("start:" + start);
//                JSONObject dayLine2 = new GetJson().getHttpJson(address, 1);
                
//                System.out.println("start:" + dayLine2);
                    
                    
//                    JSONArray json = dayLine.getJSONArray("data");
//                    List<Movie> list = JSON.parseArray(json.toString(), Movie.class);

                    if (start <= end){
                        System.out.println("已经爬取到底了");
//                        sqlSession.close();
                    }
//                    for (Movie movie : list) {
////                        movieMapper.insert(movie);
//                    	System.out.println(movie.toString()+"-------------------------");
////                        sqlSession.commit();
//                    }
//                    total += list.size();
                    System.out.println("正在爬取中---共抓取:" + total + "条数据");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        
        String address2 =  "http://www.sz.gov.cn/saqscjdglj/zwgk/xxgkmu/qt/gzdt/201912/W020191217520095740604.png";
    	GenerateImage(address2,"test");
        
    }
	 
	 /**
	 * 处理某一页的漏洞公告
	 * @param leakListDoc
	 */
	public static void solvePageData(Document leakListDoc) {
		/*Elements ulElements = ((Element) leakListDoc).getElementsByClass("view_title");
		 for(int i=0;i<ulElements.size();i++){
			 Elements liElements = ulElements.get(i).getElementsByTag("li");
			 for(int j=0;j<liElements.size();j++) {
				 String onclickvalue = liElements.get(j).toString();
				 onclickvalue = onclickvalue.substring(85, 159);
				 System.out.println(onclickvalue);
				 
			 }
		 }
		 String str = new String();
		 Elements TRS_Editor_ulElements = ((Element) leakListDoc).getElementsByClass("TRS_Editor");
		 List<Node> node = TRS_Editor_ulElements != null ? TRS_Editor_ulElements.get(0).childNodes():null;
		 if(node!=null) {
			 for(int j=0;j<node.size();j++) {
					Node d = node.get(j) ;
					String dd = d.nodeName();
					if(d.nodeName().equals("p")) {
						Element r = (Element) node.get(j);
						str = str + r.text();
					}
				}
		 }
		 System.out.println(str);
		 
		 for(int i=0;i<TRS_Editor_ulElements.size();i++){
//				Elements liElements = TRS_Editor_ulElements.get(i).getElementsByTag("p");
//				Elements g = TRS_Editor_ulElements.get(i).getElementsByAttribute("P");
//				Element aa = TRS_Editor_ulElements.get(i).child(i);
//				System.out.println(aa.toString()); 
//				System.out.println(aa.text()); 
			
			 List<Node> aaa = TRS_Editor_ulElements.get(i).childNodes();
			for(int j=0;j<aaa.size();j++) {
				Node d = aaa.get(j) ;
				String dd = d.nodeName();
				System.out.println(dd);
				
				if(d.nodeName().equals("p")) {
					Element r = (Element) aaa.get(j);
					System.out.println(r.toString());
					System.out.println(r.text());
				}
			}
		 }
		 String dd = TRS_Editor_ulElements.text();
//			 TRS_Editor_ulElements.attr(attributeKey);
		 System.out.println(dd);
		 System.out.println(ulElements.get(0));
		 System.out.println(ulElements.get(0));
		 */
		
/*		 list_item
		Elements list_boxElements = ((Element) leakListDoc).getElementsByClass("list_item");//获取每条路径
		List<Node> node = list_boxElements != null ? list_boxElements.get(0).childNodes():null;
		String ddd = list_boxElements.attr("href");
		
		for (int i = 0; i < list_boxElements.size(); i++) {
			String a = list_boxElements.get(i).attr("href");
			System.out.println(a.substring(1));
			Elements dd = list_boxElements.get(i).getElementsByTag("font");
			System.out.println(dd.text());
		}
		
		System.out.println(list_boxElements.attr("href").substring(1));
		System.out.println(list_boxElements);
		System.out.println(list_boxElements.text());
		System.out.println(node);
		System.out.println(list_boxElements.text());
*/
		//获取爬虫的图片和需要的数据
/*
		System.out.println(leakListDoc);
		Elements ulElements = ((Element) leakListDoc).getElementsByClass("view_title");
		 for(int i=0;i<ulElements.size();i++){
			 Elements liElements = ulElements.get(i).getElementsByTag("li");
			 for(int j=0;j<liElements.size();j++) {
				 String onclickvalue = liElements.get(j).toString();
				 onclickvalue = onclickvalue.substring(85, 159);
				 System.out.println(onclickvalue);
				 
			 }
		 }
		 String str = new String();
		 Elements TRS_Editor_ulElements = ((Element) leakListDoc).getElementsByClass("TRS_Editor");
		 List<Node> node = TRS_Editor_ulElements != null ? TRS_Editor_ulElements.get(0).childNodes():null;
		 if(node!=null) {
			 for(int j=0;j<node.size();j++) {
					Node d = node.get(j) ;
					String dd = d.nodeName();
					if(d.nodeName().equals("p")) {
						Element r = (Element) node.get(j);
						String oldsrc = r.getElementsByTag("img").attr("OLDSRC");
						String text = r.text();
						System.out.println(oldsrc+"==="+text);
						if(oldsrc!=null && !"".equals(oldsrc)) {
							str = str + "@img "+ oldsrc+"@imgText";
						}else {
							str = str + text+"@imgText";
						}
					}
				}
		 }
		 System.out.println(str);
		 
		 String[] strs = str.split("@imgText");
		 
	     System.out.println(strs); 
		
 */
	}
 
	
	//base64字符串转化成图片
    public static String GenerateImage(String imgStr,String pk)
    {   
        System.out.print("已经收到了把字节码转化为图片的方法");
        //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return "error";
        
        //解析base64码，获取图片格式
        String str [] = imgStr.split(",");
        imgStr = str[1];
        String imgInfo = str[0];
        String imgExt = imgInfo.split("/")[1].split(";")[0];
        
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            String imgFilePath = "/SCApp/images/"+pk+"."+imgExt;//新生成的图片
            System.out.println(imgFilePath);
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return imgExt;
        }
        catch (Exception e)
        {
            return "error";
        }
    }
}
