package com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.service;

import com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.pojo.entity.City;
import com.yscyber.myspringcloud.projectd.msawrweatherdatacollectionserver.pojo.json.WeatherJsonObject;
import java.util.List;

/**
 * @author Yuan
 */
public interface WeatherService {

    /**
     * 从第三方 API 获取某个城市的天气数据
     *
     * @param cityKey 城市编号
     * @return WeatherJsonObject
     */
    WeatherJsonObject getWeatherDataByCityKeyFromInternet(String cityKey);

    /**
     * 将第三方 API 获取的数据同步到本地
     *
     * 前提是：已经得到“城市”的数据
     */
    void syncWeatherData();

    /**
     * 将第三方 API 获取的数据同步到本地
     *
     * @param cityKey 城市编号
     */
    void syncWeatherData(String cityKey);

    /**
     * 将第三方 API 获取的数据同步到本地
     *
     * @param cityKeyList 城市编号列表
     */
    void syncWeatherData(List<String> cityKeyList);

    /**
     * 将第三方 API 获取的数据同步到本地
     *
     * @param city City
     */
    void syncWeatherDataByCity(City city);

    /**
     * 将第三方 API 获取的数据同步到本地
     *
     * @param cityList City 列表
     */
    void syncWeatherDataByCity(List<City> cityList);

}