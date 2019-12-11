package com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.dao;

import com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.pojo.json.WeatherJsonObject;

/**
 * @author Yuan
 */
public interface WeatherDAO {

    /**
     * 从第三方 API 获取某个城市的天气数据
     *
     * @param cityKey 城市编号
     * @return WeatherJsonObject
     */
    WeatherJsonObject getWeatherJsonObjectByCityKeyFromInternet(String cityKey);

}