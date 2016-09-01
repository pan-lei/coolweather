package com.coolweather.app.util;

import com.coolweather.app.model.City;
import com.coolweather.app.model.CoolWeatherDB;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import android.text.TextUtils;

public class Utility {
	
	/*
	 * 解析和处理服务器返回的省级数据
	 */
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB, String response) {
		
		if(!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if(allProvinces != null && allProvinces.length>0) {
				for(String p : allProvinces) {
					String[] array = p.split("\\|");		//两次分割，分割出一个保存一个
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					//将解析出来的数据保存到Provinc表
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 解析和处理服务器返回的市级数据
	 */
	public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId) {
		
		if(!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if(allCities != null && allCities.length>0) {
				for(String c : allCities) {
					String[] array = c.split("\\|");		//两次分割，分割出一个保存一个
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					//将解析出来的数据保存到City表
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 解析和处理服务器返回的县级数据
	 */
	public synchronized static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response, int cityId) {
		
		if(!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if(allCounties != null && allCounties.length>0) {
				for(String p : allCounties) {
					String[] array = p.split("\\|");		//两次分割，分割出一个保存一个
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					//将解析出来的数据保存到City表
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
	
}
