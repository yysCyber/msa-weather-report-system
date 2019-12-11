package com.yscyber.myspringcloud.projectd.msawrweatherdataserver.dao;

import com.yscyber.myspringcloud.projectd.msawrweatherdataserver.pojo.json.WeatherJsonObject;

/**
 * @author Yuan
 */
public interface WeatherDAO {

    WeatherJsonObject getWeatherByCityKey(String cityKey);

}