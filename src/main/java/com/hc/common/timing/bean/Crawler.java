package com.hc.common.timing.bean;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hc.common.redis.RedisUtil;
import com.hc.common.timing.SpringUtil;
import com.hc.pojo.resBean.ResGetOneTyphoonWarningBean;
import com.hc.pojo.resBean.ResGetTyphoonWarningsBean;
import com.hc.pojo.resBean.ResOneDayDetailsWeatherBean;
import com.hc.pojo.resBean.ResOneDayWeatherBean;
import com.hc.pojo.resBean.ResSevenDaysWeatherBean;
import com.hc.test.GetJson;

public class Crawler {
	@Autowired
	private static RedisUtil redisUtil = SpringUtil.getBean(RedisUtil.class);
	//缓存获取台风预警详情
	public static ResGetTyphoonWarningsBean getRedisTyphoonWarning() throws Exception {
		ResGetTyphoonWarningsBean bean = (ResGetTyphoonWarningsBean)redisUtil.get("crawler:typhoon:ResGetTyphoonWarningsBean");
		if(null==bean){
			bean = getTyphoonWarning();
		}
		return bean;
	}
	//缓存获取天气预报详情
	public static ResSevenDaysWeatherBean getRedisWeatherForecastDetails() throws Exception {
		ResSevenDaysWeatherBean bean = (ResSevenDaysWeatherBean)redisUtil.get("crawler:weather:ResSevenDaysWeatherBean");
		if(null==bean){
			bean = getWeatherForecastDetailsCrawler();
		}
		return bean;
	}
	// 爬取台风预警详情
	public static ResGetTyphoonWarningsBean getTyphoonWarning() throws Exception {
		ResGetTyphoonWarningsBean bean = new ResGetTyphoonWarningsBean();
		List<ResGetOneTyphoonWarningBean> beanList = new ArrayList<ResGetOneTyphoonWarningBean>();
		String emergencyNewsJsonString = new GetJson()
				.getHttpJson2("http://www.nmc.cn/publish/country/warning/typhoon.html", 1);
		org.jsoup.nodes.Document emergencyNewsDocument = Jsoup.parse(emergencyNewsJsonString);
		Element mylistcarousel = ((Element) emergencyNewsDocument).getElementById("mylistcarousel");// 获取每条路径
		Elements elements_li = mylistcarousel.getElementsByTag("li");
		for (int i = 0; i < elements_li.size(); i++) {
			ResGetOneTyphoonWarningBean typhoonWarning = new ResGetOneTyphoonWarningBean();
			String timeTitle = elements_li.get(0).getElementsByTag("p").get(0).text();
			String interfaces = elements_li.get(0).getElementsByTag("p").get(0).attr("data-id");
			typhoonWarning.setTimeTitle(timeTitle);
			typhoonWarning.setInterfaces("http://www.nmc.cn/f/rest/getContent?dataId=" + interfaces);
			beanList.add(typhoonWarning);
			String emergencyNewsJsonString_text = new GetJson().getHttpJson2(typhoonWarning.getInterfaces(), 1);
			org.jsoup.nodes.Document emergencyNewsDocument_text = Jsoup.parse(emergencyNewsJsonString_text);
			Elements bean_title = ((Element) emergencyNewsDocument_text).getElementsByClass("title");
			typhoonWarning.setTitle(bean_title.get(0).text());
			Elements bean_author = ((Element) emergencyNewsDocument_text).getElementsByClass("author");
			typhoonWarning.setAuthor(bean_author.get(0).text());
			Elements bean_writing = ((Element) emergencyNewsDocument_text).getElementsByClass("writing");
			Elements bean_writing_ps = bean_writing.get(0).getElementsByTag("p");
			List<String> textList = new ArrayList<String>();
			for (int j = 0; j < bean_writing_ps.size(); j++) {
				String text = bean_writing_ps.get(j).html();
				if (!"".equals(text)) {
					textList.add(text);
				}
			}
			typhoonWarning.setTextList(textList);
			Elements bean_writing_div = bean_writing.get(0).getElementsByTag("div");
			List<String> imgList = new ArrayList<String>();
			for (int k = 0; k < bean_writing_div.size(); k++) {
				String img = bean_writing_div.get(k).getElementsByTag("img").attr("src");
				if (!"".equals(img)) {
					imgList.add(img);
				}
			}
			typhoonWarning.setImgList(imgList);
		}
		bean.setListBean(beanList);
		System.out.println(JSON.toJSONString(bean));
		redisUtil.set("crawler:typhoon:ResGetTyphoonWarningsBean", bean);
		return bean;
	}

	// 爬虫获取天气预报详情
	public static ResSevenDaysWeatherBean getWeatherForecastDetailsCrawler() throws Exception {
		ResSevenDaysWeatherBean bean = new ResSevenDaysWeatherBean();
		List<ResOneDayWeatherBean> beanList = new ArrayList<ResOneDayWeatherBean>();
		String emergencyNewsJsonString = new GetJson()
				.getHttpJson2("http://www.nmc.cn/publish/forecast/AGD/shenzhen.html", 1);
		org.jsoup.nodes.Document emergencyNewsDocument = Jsoup.parse(emergencyNewsJsonString);
		Element forecast = ((Element) emergencyNewsDocument).getElementById("forecast");// 获取每条路径
		Elements list_detail = forecast.getElementsByClass("detail");
		list_detail = list_detail != null ? list_detail : null;
		for (int i = 0; i < list_detail.size(); i++) {
			/**
			 * 七天天气预报
			 ***/
			ResOneDayWeatherBean dayWeatherBean = new ResOneDayWeatherBean();
			Elements list_tr = list_detail.get(i).getElementsByTag("tr");
			Elements list_td_tr0 = list_tr.get(0).getElementsByTag("td");
			Elements list_p_td0_tr0 = list_td_tr0.get(0).getElementsByTag("p");
			if (list_p_td0_tr0.size() == 1 && "".equals(list_p_td0_tr0.get(0).text())) {
				// 日期
				dayWeatherBean.setDate(list_td_tr0.get(0).text());
				// 星期
				dayWeatherBean.setWeek(list_td_tr0.get(1).text());
				// 日期名称
				dayWeatherBean.setDateName("");
			} else {
				// 日期名称
				dayWeatherBean.setDateName(list_p_td0_tr0.get(0).text());
				// 星期
				dayWeatherBean.setWeek(list_p_td0_tr0.get(1).text());
				// 日期
				dayWeatherBean.setDate(list_td_tr0.get(1).text());
			}
			// 天气现象img
			Elements list_td_tr1 = list_tr.get(1).getElementsByTag("td");
			dayWeatherBean.setWicon(list_td_tr1.get(0).attr("src"));
			if (list_td_tr1.size() > 1) {
				dayWeatherBean.setWicon2(list_td_tr1.get(1).attr("src"));
			}
			// 天气现象
			Elements list_td_tr2 = list_tr.get(2).getElementsByTag("td");
			dayWeatherBean.setWdesc(list_td_tr2.get(0).text());
			if (list_td_tr2.size() > 1) {
				dayWeatherBean.setWdesc2(list_td_tr2.get(1).text());
			}
			// 气温
			Elements list_td_tr3 = list_tr.get(3).getElementsByTag("td");
			dayWeatherBean.setTemp(list_td_tr3.get(0).text());
			if (list_td_tr3.size() > 1) {
				dayWeatherBean.setTemp2(list_td_tr3.get(1).text());
			}
			// 风向
			Elements list_td_tr4 = list_tr.get(4).getElementsByTag("td");
			dayWeatherBean.setDirect(list_td_tr4.get(0).text());
			if (list_td_tr4.size() > 1) {
				dayWeatherBean.setDirect2(list_td_tr4.get(1).text());
			}
			// 风度
			Elements list_td_tr5 = list_tr.get(5).getElementsByTag("td");
			dayWeatherBean.setWind(list_td_tr5.get(0).text());
			if (list_td_tr5.size() > 1) {
				dayWeatherBean.setWind2(list_td_tr5.get(1).text());
			}

			/**
			 * 精细预报
			 ***/
			ResOneDayDetailsWeatherBean weatherBean = new ResOneDayDetailsWeatherBean();
			Element hour3 = ((Element) emergencyNewsDocument).getElementById("hour3");
			Element hour3_day = hour3.getElementById("day" + i);
			// 精细预报标题
			Elements hour3_day_first = hour3_day.getElementsByClass("row first");
			Elements hour3_day_first_divs = hour3_day_first.get(0).getElementsByTag("div");
			for (int j = 1; j < hour3_day_first_divs.size(); j++) {
				weatherBean.getForecast()[j - 1] = hour3_day_first_divs.get(j).text();
			}
			// 天气现象
			Elements hour3_day_secondTqxx = hour3_day.getElementsByClass("row second tqxx");
			Elements hour3_day_secondTqxx_divs = hour3_day_secondTqxx.get(0).getElementsByTag("div");
			for (int j = 1; j < hour3_day_secondTqxx_divs.size(); j++) {
				String img = hour3_day_secondTqxx_divs.get(j).getElementsByTag("img").attr("src");
				if (null != img && !"".equals(img)) {
					weatherBean.getWeatherPhenomenon()[j - 1] = img;
				} else {
					weatherBean.getWeatherPhenomenon()[j - 1] = hour3_day_secondTqxx_divs.get(j).text();
				}
			}
			// 气温
			Elements hour3_day_wd = hour3_day.getElementsByClass("row wd");
			Elements hour3_day_wd_divs = hour3_day_wd.get(0).getElementsByTag("div");
			for (int j = 1; j < hour3_day_wd_divs.size(); j++) {
				weatherBean.getTemperature()[j - 1] = hour3_day_wd_divs.get(j).text();
			}
			// 降水
			Elements hour3_day_js = hour3_day.getElementsByClass("row js");
			Elements hour3_day_js_divs = hour3_day_js.get(0).getElementsByTag("div");
			for (int j = 1; j < hour3_day_js_divs.size(); j++) {
				weatherBean.getPrecipitation()[j - 1] = hour3_day_js_divs.get(j).text();
			}
			// 风速
			Elements hour3_day_winds = hour3_day.getElementsByClass("row winds");
			Elements hour3_day_winds_divs = hour3_day_winds.get(0).getElementsByTag("div");
			for (int j = 1; j < hour3_day_winds_divs.size(); j++) {
				weatherBean.getWindSpeed()[j - 1] = hour3_day_winds_divs.get(j).text();
			}
			// 风向
			Elements hour3_day_windd = hour3_day.getElementsByClass("row windd");
			Elements hour3_day_windd_divs = hour3_day_windd.get(0).getElementsByTag("div");
			for (int j = 1; j < hour3_day_windd_divs.size(); j++) {
				weatherBean.getDirectionOfTheWind()[j - 1] = hour3_day_windd_divs.get(j).text();
			}
			// 气压
			Elements hour3_day_qy = hour3_day.getElementsByClass("row qy");
			Elements hour3_day_qy_divs = hour3_day_qy.get(0).getElementsByTag("div");
			for (int j = 1; j < hour3_day_qy_divs.size(); j++) {
				weatherBean.getAirPressure()[j - 1] = hour3_day_qy_divs.get(j).text();
			}
			// 相对湿度
			Elements hour3_day_xdsd = hour3_day.getElementsByClass("row xdsd");
			Elements hour3_day_xdsd_divs = hour3_day_xdsd.get(0).getElementsByTag("div");
			for (int j = 1; j < hour3_day_xdsd_divs.size(); j++) {
				weatherBean.getRelativeHumidity()[j - 1] = hour3_day_xdsd_divs.get(j).text();
			}
			// 云量
			Elements hour3_day_yl = hour3_day.getElementsByClass("row yl");
			Elements hour3_day_yl_divs = hour3_day_yl.get(0).getElementsByTag("div");
			for (int j = 1; j < hour3_day_yl_divs.size(); j++) {
				weatherBean.getCloudCover()[j - 1] = hour3_day_yl_divs.get(j).text();
			}
			// 能见度
			Elements hour3_day_njd = hour3_day.getElementsByClass("row njd");
			Elements hour3_day_njd_divs = hour3_day_njd.get(0).getElementsByTag("div");
			for (int j = 1; j < hour3_day_njd_divs.size() - 1; j++) {
				weatherBean.getVisibility()[j - 1] = hour3_day_njd_divs.get(j + 1).text();
			}
			dayWeatherBean.setWeatherBean(weatherBean);
			beanList.add(dayWeatherBean);
		}
		bean.setBeanList(beanList);
		System.out.println(JSON.toJSONString(bean));
		redisUtil.set("crawler:weather:ResSevenDaysWeatherBean", bean);
		return bean;
	}
}
