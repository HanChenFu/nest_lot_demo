package com.hc.pojo.resBean;

public class ResOneDayDetailsWeatherBean {
	private String[] forecast = new String[9];  //精细预报
	private String[] weatherPhenomenon = new String[9]; //天气现象
	private String[] temperature = new String[9]; //气温
	private String[] precipitation = new String[9]; //降水
	private String[] windSpeed = new String[9]; //风速
	private String[] directionOfTheWind = new String[9]; //风向
	private String[] airPressure = new String[9]; //气压
	private String[] relativeHumidity = new String[9]; //相对湿度
	private String[] cloudCover = new String[9]; //云量
	private String[] visibility = new String[9]; //能见度
	public String[] getForecast() {
		return forecast;
	}
	public void setForecast(String[] forecast) {
		this.forecast = forecast;
	}
	public String[] getWeatherPhenomenon() {
		return weatherPhenomenon;
	}
	public void setWeatherPhenomenon(String[] weatherPhenomenon) {
		this.weatherPhenomenon = weatherPhenomenon;
	}
	public String[] getTemperature() {
		return temperature;
	}
	public void setTemperature(String[] temperature) {
		this.temperature = temperature;
	}
	public String[] getPrecipitation() {
		return precipitation;
	}
	public void setPrecipitation(String[] precipitation) {
		this.precipitation = precipitation;
	}
	public String[] getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(String[] windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String[] getDirectionOfTheWind() {
		return directionOfTheWind;
	}
	public void setDirectionOfTheWind(String[] directionOfTheWind) {
		this.directionOfTheWind = directionOfTheWind;
	}
	public String[] getAirPressure() {
		return airPressure;
	}
	public void setAirPressure(String[] airPressure) {
		this.airPressure = airPressure;
	}
	public String[] getRelativeHumidity() {
		return relativeHumidity;
	}
	public void setRelativeHumidity(String[] relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}
	public String[] getCloudCover() {
		return cloudCover;
	}
	public void setCloudCover(String[] cloudCover) {
		this.cloudCover = cloudCover;
	}
	public String[] getVisibility() {
		return visibility;
	}
	public void setVisibility(String[] visibility) {
		this.visibility = visibility;
	}
	
}
