package com.hc.pojo.resBean;

public class ResSevenDaysWeatherBean {
	private ResOneDaysWeatherBean[] oneDaysWeatherBeans = new ResOneDaysWeatherBean[7];

	public ResOneDaysWeatherBean[] getOneDaysWeatherBeans() {
		return oneDaysWeatherBeans;
	}
	public void setOneDaysWeatherBeans(ResOneDaysWeatherBean[] oneDaysWeatherBeans) {
		this.oneDaysWeatherBeans = oneDaysWeatherBeans;
	}
}
