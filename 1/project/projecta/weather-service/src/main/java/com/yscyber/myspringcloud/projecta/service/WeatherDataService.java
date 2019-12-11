package com.yscyber.myspringcloud.projecta.service;

import com.yscyber.myspringcloud.projecta.pojo.entity.WeatherResponse;

/**
 * 获取天气数据服务
 */
public interface WeatherDataService {

    /**
     * 根据“城市名称”获取天气信息
     *
     * @param cityName 城市名
     * @return WeatherResponse
     */
    WeatherResponse getWeatherDataByCityName(String cityName);

    /**
     * 根据“城市编号”获取天气信息
     *
     * @param cityKey 城市编号
     * @return WeatherResponse
     */
    WeatherResponse getWeatherDataByCityKey(String cityKey);

    /**
     * 根据“城市编号”将天气信息同步到 Redis 缓存中
     *
     * @param cityKey 城市编号
     */
    void syncWeatherDataByCityKey(String cityKey);

}