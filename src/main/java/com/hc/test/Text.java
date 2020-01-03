package com.hc.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.hc.common.tools.DownloadImage;
import com.hc.common.tools.Tools;
import com.hc.pojo.entity.TbWorkDynamics;
import com.hc.utils.conig.SystemConfigUtil;

public class Text {
	private static String noticesUrl = "http://yjgl.sz.gov.cn/zwgk/xxgkmu/qt/tzgg/";  //通知公告
	public static void main(String[] args) throws Exception {
		String dateTime = "/"+new SimpleDateFormat("yyyyMMdd").format(new Date());
        String base = "D:/upload/";
		String notices_path = base + "reptile_notices_aaaaaaa" + dateTime;
		Integer index = 0;
		//通知公告
		String noticesJsonString = new GetJson().getHttpJson2(noticesUrl, 1);
		/*System.out.println(noticesJsonString);*/
		org.jsoup.nodes.Document noticesDocument = Jsoup.parse(noticesJsonString);
		List<String> noticesList = allCaseListItem(noticesDocument);
		for (int i = 0; i < noticesList.size(); i++) {
			/*System.out.println(noticesJsonString);*/
			String[] hrefAndtimes = noticesList.get(i).split("@Time");
			TbWorkDynamics wd = new TbWorkDynamics();
			String address = noticesUrl+hrefAndtimes[0] ;
			String dayLine = new GetJson().getHttpJson2(address, 1);
			org.jsoup.nodes.Document dayLineDocument = Jsoup.parse(dayLine);
			wd = solvePageData(dayLineDocument,wd);
			wd.setCreateTime(Tools.getAPIresponseDateTime());//当前时间
			wd.setDataTime(hrefAndtimes[1]);//时间
			//index = tbNoticeMapper.insertNotice(wd);
			/*System.out.println(index+"===========================");*/
			if(wd.getTempImgUrl()!=null && !"".equals(wd.getTempImgUrl())) {
				String[] tempImgUrls = wd.getTempImgUrl().split("@img");
				for (int j = 0; j < tempImgUrls.length; j++) {
					String filename = address.substring(0,address.lastIndexOf("/")+1);
					DownloadImage.download(filename+tempImgUrls[j], notices_path);
				}
			}
		}
	}
	/**
	 * 获取每个动态中的每一条数据   (工作动态、通知公告、各区动态、应急要闻)
	 * @param leakListDoc
	 * @param tbWorkDynamics
	 * @return
	 */
	public static TbWorkDynamics solvePageData(Document leakListDoc,TbWorkDynamics tbWorkDynamics) {
	 String dateTime = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/";
	 Elements ulElements = ((Element) leakListDoc).getElementsByClass("view_title");
	 String str = new String();
	 String strImgUrl = new String ();
	 Elements TRS_Editor_ulElements = ((Element) leakListDoc).getElementsByClass("TRS_Editor");
	 List<Node> node = TRS_Editor_ulElements != null ? TRS_Editor_ulElements.get(0).childNodes():null;
	 if(node!=null) {
		 for(int j=0;j<node.size();j++) {
				Node d = node.get(j) ;
//				String dd = d.nodeName();
				if(d.nodeName().equals("p")) {
					Element r = (Element) node.get(j);
					String oldsrc = r.getElementsByTag("img").attr("OLDSRC");//获取图片
					String text = r.text();//获取内容
//					System.out.println(oldsrc+"==="+text);
					if(oldsrc!=null && !"".equals(oldsrc)) {
						str = str + "@img "+ dateTime+oldsrc + "@imgText";
						strImgUrl = strImgUrl + oldsrc + "@img";
					}else {
						str = str + text+"@imgText";
					}
				}
			}
	 }
//	 System.out.println(str);
	 tbWorkDynamics.setTbTitle(ulElements.text());
	 tbWorkDynamics.setTbContent(str);
	 tbWorkDynamics.setTempImgUrl(strImgUrl);
//	 String dd = TRS_Editor_ulElements.text();
	 return tbWorkDynamics;
	}
	/**
	 * 获取每条动态的信息 (工作动态、通知公告、各区动态、应急要闻)
	 * @param leakListDoc
	 * @return
	 */
	public static List<String> allCaseListItem(Document leakListDoc){
		
		Elements list_boxElements = ((Element) leakListDoc).getElementsByClass("list_item");//获取每条路径
		list_boxElements = list_boxElements != null ? list_boxElements : null;
//		String ddd = list_boxElements.attr("href");
//		Map<String,String> m1 =new HashMap<String,String>();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < list_boxElements.size(); i++) {
			String href = list_boxElements.get(i).attr("href");
			System.out.println(href);
			String  time = list_boxElements.get(i).getElementsByTag("font").text();
			if(href!=null && !"".equals(href) && (time!=null && !"".equals(time))) {
				String hrefAndtimes = (href == null ? "" : href.substring(1)) +"@Time" + (time == null ? "" : time);
				System.out.println(hrefAndtimes);
				list.add(hrefAndtimes);
//				System.out.println(href.substring(1)+"===="+time);
//				m1.put(href.substring(1), time);
			}
		}
		return list;
	}  
}
